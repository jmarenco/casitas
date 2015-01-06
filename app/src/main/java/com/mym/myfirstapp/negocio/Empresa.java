package com.mym.myfirstapp.negocio;

/**
 * Created by jmarenco on 1/5/15.
 */
public class Empresa extends Objeto
{
    // Tipo de las empresas
    public enum Tipo { Luz, Gas, Telefono };

    // Tipo de la empresa
    private Tipo _tipo;


    // Constructor
    public Empresa(Tipo tipo, double x, double y)
    {
        super(x,y);
        _tipo = tipo;
    }

    // Getters
    public Tipo getTipo()
    {
        return _tipo;
    }
}
