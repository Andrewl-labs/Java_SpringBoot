package org.example.service;

import org.example.dao.MemberDAO;
import org.example.domain.Book;
import org.example.exceptions.NoBookAvailableException;

import java.util.List;

public class MemberService {
    private final MemberDAO mem;

    public MemberService() {
        this.mem = new MemberDAO();
    }

    public List<Book> viewCatalog(){
        return mem.viewCatalog();
    }

    public void loanBook(int memberId, int bookId){
        boolean available = mem.checkBookAvailability(bookId);
        if (!available){
            throw new NoBookAvailableException("No hay copias disponibles para el libro o no existe.");
        }
        mem.loanBook(memberId, bookId,1);
    }

    public void returnBook(int memberId, int bookId){
        boolean bookLoaned = mem.checkLoanByUser(memberId, bookId);
        if (!bookLoaned){
            throw new NoBookAvailableException("El socio no tiene prestado este libro.");
        }
        mem.returnBook(memberId, bookId);
    }
}
