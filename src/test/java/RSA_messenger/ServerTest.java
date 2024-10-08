package RSA_messenger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    private  Server server;

    @BeforeEach
    void setUp() {
        server = new Server();
    }

    @AfterEach
    void tearDown() {
        server = null;
    }

    @DisplayName("1. New user is added to the server's map")
    @Test
    void givenAUserThenAddedCorrectly(){
        // Arrange
        User user1 = new User("Lucas", new KeyPair(2,5));

        // Act
        server.addUser(user1);

        // Assert
        assertTrue(server.getUsersSet().contains(user1));
    }

    @DisplayName("2. Two users in the server are returned correctly by getUsersSet")
    @Test
    void givenTwoUsersWhenTryToGetUsersSetThenReturnsCorrectly(){
        // Arrange
        User user1 = new User("Lucas", new KeyPair(2,5));
        User user2 = new User("Danichelo", new KeyPair(1,6));

        Set<User> resultSet = new TreeSet<>();
        resultSet.add(user1);
        resultSet.add(user2);

        // Act
        server.addUser(user1);
        server.addUser(user2);

        // Assert
        assertEquals(resultSet, server.getUsersSet());
    }

    @DisplayName("3. getUsersSet returns correctly sorted by name set")
    @Test
    void givenTwoUsersWhenTryToGetUsersSetThenReturnsCorrectlySortedSet(){
        // Arrange
        User user1 = new User("Lucas", new KeyPair(2,5));
        User user2 = new User("Danichelo", new KeyPair(1,6));

        // Act
        server.addUser(user1);
        server.addUser(user2);

        SortedSet<User> usersSet = (SortedSet<User>) server.getUsersSet();
        User dani = usersSet.first();

        // Assert
        assertEquals("DANICHELO", dani.getUserName());
    }

    @DisplayName("4. When a server is created it has zero users")
    @Test
    void givenAnEmptyServerWhenCheckTheSizeThenItsZero(){
        assertEquals(0, server.getUsersSet().size());
    }

    @DisplayName("5. When add a user and then remove it then the total size its zero")
    @Test
    void givenAServerWithOneUserWhenRemoveItThenServerItsEmpty(){
        // Arrange
        User user = new User("Lucas", new KeyPair(2,2));

        // Act
        server.addUser(user);
        server.removeUser(user);

        // Assert
        assertEquals(0, server.getUsersSet().size());
    }
}