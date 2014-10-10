/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.TransferObjects;

/**
 *
 * @author ammar
 */
public interface User {

    void checkLogin(String username, String password);

    String getUsername();

    String getType();

    int getScore(int topicId);
}
