package com.mym.myfirstapp.negocio;

import java.util.ArrayList;

public class Nivel
{
    // Numero de nivel
    private int _numero;

    // Casitas y empresas
    private ArrayList<Casita> _casitas;
    private ArrayList<Empresa> _empresas;

    // Constructor
    public Nivel(int numero)
    {
        _numero = numero;
        _casitas = new ArrayList<Casita>();
        _empresas = new ArrayList<Empresa>();
    }

    // Agrega elementos al nivel
    public void addCasita(Casita casita)
    {
        _casitas.add(casita);
    }
    public void addEmpresa(Empresa empresa)
    {
        _empresas.add(empresa);
    }
}
