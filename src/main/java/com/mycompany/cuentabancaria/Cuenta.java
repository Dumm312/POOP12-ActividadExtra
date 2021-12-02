/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cuentaBancaria;


/**
 *Account Manager
 *@author Daniel Medrano, Angel Jimenez, Ariana Alvarez, Melisa Matias,Luis Gustavo
 */
public class Cuenta extends Thread{
    private static double saldo = 0;
/**
 * Constructor for the account
 * @param nombre 
 */
    public Cuenta(String nombre) {
        super(nombre);
        
    }

    @Override
    public void run() { 
        if(getName().equals("Deposito 1") || getName().equals("Deposito 2")){
            this.depositarDinero(100);
        }else{
            this.retirarDinero(50);
        }
         System.out.println("Termina el hilo "+getName());
    }
    
    /**
     * Deposit an amount of money in the account
     * @param monto 
     */
    public synchronized void depositarDinero(double monto){
        
        saldo+=monto;
        System.out.println("Se depositaron "+monto+" pesos \nSaldo restante "+saldo+" pesos");
        notifyAll();
    }
    /**
     * Withdraw an amount of money in the account
     * @param monto 
     */
    public synchronized void retirarDinero(double monto){
        try {
            if (saldo <= 0) {
                System.out.println(getName() + " no tiene saldo, \nsaldo = " + saldo);

                sleep(5000);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        saldo -= monto;
        System.out.println(getName()+" retiró "+monto+" pesos. \nSaldo restante "+saldo+" pesos");
        notifyAll();
    }
    
    public static void main(String[] args) {
        /**
         * Implementacion de los hilos de la clase Cuenta
         */
       new Cuenta("Retiro 1").start();
       new Cuenta("Retiro 2").start();
       new Cuenta("Deposito 1").start();
       new Cuenta("Deposito 2").start();
       
       try{
           sleep(15000);
           System.out.println("Saldo Final "+saldo);
       }catch(InterruptedException ex){
           System.out.println(ex.getMessage());
       }
    }
    
}
