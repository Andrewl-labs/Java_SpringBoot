package org.example.service;

import org.example.dao.UserDAO;
import org.example.domain.Book;
import org.example.domain.Loan;
import org.example.domain.Person;
import org.example.exceptions.MemberNotFound;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public Person searchMember(int id){

        Person member = userDAO.searchMember(id);
        if (member == null){
            throw new MemberNotFound("El socio con id " + id + " no está registrado.");
        } else {
            return member;
        }
    }

    public void addBook(Book newBook){
        userDAO.addBook(newBook);
    }

    public List<Loan> viewLoans(){
        return userDAO.viewLoans();
    }

    public void registerMember(Person newMember){
        userDAO.registerMember(newMember);
    }

    public void toggleMemberStatus(int id){
        boolean status = userDAO.checkMemberStatus(id);
        userDAO.toggleMemberStatus(id, !status);
    }

    public List<Book> viewCatalog(){
        return userDAO.viewCatalog();
    }
}
