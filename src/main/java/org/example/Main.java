package org.example;

public class Main {
  public static void main(String[] args) {
    UserDAO dao = new UserDAO();

    //criando o usuário
    User user = new User();
    user.setName("Erick Moreira");
    user.setEmail("Herick@email.com");
    dao.createUser(user);

    //buscando usuário
    User fetchedUser = dao.getUserById(user.getId());
    System.out.println("Usuário buscado: " + fetchedUser);

    //listando usuários
    System.out.println("Lista de usuários: " + dao.listUsers());

    // atualizando informações do usuário
    user.setName("Herick Moreira");
    user.setEmail("Herick.Moreira@email.com");
    dao.updateUser(user);
    System.out.println("Usuário atualizado: " + dao.getUserById(user.getId()));


    //deletando usuário
    dao.deleteUser(user.getId());
    System.out.println("Usuário deletado: " + dao.getUserById(user.getId()));
  }
}