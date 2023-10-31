package org.goodie.dao;

import org.goodie.dto.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDB {
  static {
    System.out.println("DB initialized");
  }

  private static String PATH = "/home/daniil/IdeaProjects/awp/src/main/resources/data.txt";
  private static Map<UUID, User> DATA = loadFromFile();

  public static void addUser(User user) {
    user.setId(UUID.randomUUID());
    DATA.put(user.getId(), user);
  }

  public static User findById(UUID id) {
    return DATA.get(id);
  }

  public static List<User> findAll() {
    return DATA.values().stream()
            .sorted(Comparator.comparing(User::getName))
            .collect(Collectors.toList());
  }

  public static void updateUser(User user) {
    User oldUser = DATA.get(user.getId());
    oldUser.setName(user.getName());
    oldUser.setLogin(user.getLogin());
    oldUser.setPassword(user.getPassword());
    oldUser.setRestrictionFlag(user.isRestrictionFlag());

    DATA.put(oldUser.getId(), oldUser);
  }

  public static void deleteUser(UUID id) {
    DATA.remove(id);
  }

  public static void dump() {
    System.out.println("DUMP!!!");
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH)))
    {
      oos.writeObject(DATA);
    }
    catch(Exception ex){
      System.out.println(ex.getMessage());
    }
  }

  private static Map<UUID, User> loadFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH)))
    {
      Map<UUID, User> data = (Map<UUID, User>) ois.readObject();
      return new HashMap<>(data);
    }
    catch(Exception ex) {
      return new HashMap<>(Map.of(
              UUID.fromString("abe6288b-1a4b-446d-9d0e-37e943461354"),
              new User(UUID.fromString("abe6288b-1a4b-446d-9d0e-37e943461354"),
                      "ADMIN", "admin", "admin", true, false)));
    }
  }
}
