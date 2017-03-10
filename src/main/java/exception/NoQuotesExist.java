/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Bruger
 */
public class NoQuotesExist extends Exception {

    /**
     * Creates a new instance of <code>NoQuotesExist</code> without detail
     * message.
     */
    public NoQuotesExist() {
    }

    /**
     * Constructs an instance of <code>NoQuotesExist</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoQuotesExist(String msg) {
        super(msg);
    }
}
