package br.com.mba.engenharia.de.software.service.usuarios;

import br.com.mba.engenharia.de.software.exceptions.ListarContaException;
import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.repository.usuario.UsuarioRepositoryNovo;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserManager implements UserService{

    @PersistenceContext
    EntityManager entityManager;

    private UsuarioRepositoryNovo usuarioRepositoryNovo;

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    public UserManager(UsuarioRepositoryNovo usuarioRepositoryNovo) {
        this.usuarioRepositoryNovo = usuarioRepositoryNovo;
    }

    @Transactional
    public boolean salvarUsuario(Usuario user) {
        try {
            if (listarUsuario(user).size()==0){
                int id = ultimoId() + 1;
                user.setId(id);
                user.setStatus("0");
                entityManager.getTransaction().begin();
                entityManager.persist(user);
            }
            else{
                entityManager.close();
                logger.trace(String.format("Usuario duplicado"));
                throw new ListarContaException("Usuario duplicado!");
            }
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            entityManager.close();
            logger.trace(String.format("Erro %s", exception));
            return false;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    public List<Usuario> listarUsuario(Usuario users) {
        return procuraRegistro(users);
    }


    @Transactional
    public boolean habilitarUsuario(Usuario user){
        entityManager.getTransaction().begin();
        usuarioRepositoryNovo.findByTokenUsernameAndSenha(user.getUsername(), user.getSenha(), "0", user.getToken());
        CriteriaBuilder builder2 = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Usuario> cQuery2 = builder2.createCriteriaUpdate(Usuario.class);
        Root<Usuario> root2 = cQuery2.from(Usuario.class);
        cQuery2
                .set("status", "1")
                .where(builder2.equal(root2.get("id"), user.getId()));
        Query query2 = entityManager.createQuery(cQuery2);

        int result = query2.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        if (result == 1){
            return true;
        }
        else if (result == 0){
            return false;
        }
        return false;
    }

    @Transactional
    public List<Usuario> listarTodosUsuarios(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = cQuery.from(Usuario.class);
        cQuery
                .select(root);

        TypedQuery<Usuario> query = entityManager.createQuery(cQuery);
        return query.getResultList();
    }

    @Transactional
    public List<Usuario> procuraRegistro(Usuario user){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        criteriaQuery
                .select(root)
                .where(builder.and(builder.like(root.get("username"), user.getUsername()), builder.like(root.get("senha"), user.getSenha())));
        TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Transactional
    public int ultimoId(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        criteriaQuery
                .select(root);
        TypedQuery<Usuario> query = entityManager.createQuery(criteriaQuery);
        int size = query.getResultList().size();
        return query.getResultList().get(size - 1).getId();
    }

    @Transactional
    public void gerarToken(Usuario user){
        entityManager.getTransaction().begin();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Usuario> cQuery = builder.createCriteriaUpdate(Usuario.class);
        Root<Usuario> root = cQuery.from(Usuario.class);
        cQuery
                .set("token", user.getToken())
                .where(builder.equal(root.get("id"), user.getId()));
        Query query = entityManager.createQuery(cQuery);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Transactional
    public void logout(Usuario user){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Usuario> cQuery = builder.createCriteriaUpdate(Usuario.class);
        Root<Usuario> root2 = cQuery.from(Usuario.class);
        cQuery
                .set("token", "1")
                .where(builder.equal(root2.get("id"), user.getId()));
        Query query = entityManager.createQuery(cQuery);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public int updateStatus(Integer id) {
        return usuarioRepositoryNovo.updateStatus(id);
    }

    @Override
    public List<Usuario> findByIdAndToken(String token, Integer id) {
        return usuarioRepositoryNovo.findByIdAndToken(token, id);
    }

    @Override
    public Optional<Usuario> findByTokenUsernameAndSenha(String token, String username, String status, String senha) {
        return usuarioRepositoryNovo.findByTokenUsernameAndSenha(username, senha, status, token);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepositoryNovo.findAll();
    }

    @Override
    public List<Usuario> findByUsernameAndSenhaAndStatus(String username, String senha, String status) {
        return usuarioRepositoryNovo.findByUsernameAndSenhaAndStatus(username, senha, status);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepositoryNovo.save(usuario);
    }

    @Override
    public void setRepository(UsuarioRepositoryNovo usuarioRepositoryNovo) {
        this.usuarioRepositoryNovo = usuarioRepositoryNovo;
    }

    @Override
    public int count() {
        return usuarioRepositoryNovo.count() + 1;
    }

    @Override
    public Usuario findByTokenUsernameSenhaAndStatusAndUpdateStatus(String token, String username, String senha, String status) {
        return usuarioRepositoryNovo.findByTokenUsernameSenhaAndStatusAndUpdateStatus(token, username, senha, status);
    }
}




