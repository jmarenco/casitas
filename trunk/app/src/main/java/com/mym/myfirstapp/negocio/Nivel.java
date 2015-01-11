package com.mym.myfirstapp.negocio;

import java.util.ArrayList;

public class Nivel
{
    // Numero de nivel
    private int _numero;

    // Casitas y empresas
    private ArrayList<Casita> _casitas;
    private ArrayList<Empresa> _empresas;
    private ArrayList<Objeto> _objetos; // Aliasing con los anteriores

    // Constructor
    public Nivel(int numero)
    {
        _numero = numero;
        _casitas = new ArrayList<Casita>();
        _empresas = new ArrayList<Empresa>();
        _objetos = new ArrayList<Objeto>();
    }

    // Agrega elementos al nivel
    public void addCasita(Casita casita)
    {
        _casitas.add(casita);
        _objetos.add(casita);
    }
    public void addEmpresa(Empresa empresa)
    {
        _empresas.add(empresa);
        _objetos.add(empresa);
    }

    // Getters
    public ArrayList<Casita> getCasitas()
    {
        return _casitas;
    }
    public ArrayList<Empresa> getEmpresas()
    {
        return _empresas;
    }
    public ArrayList<Objeto> getObjetos()
    {
        return _objetos;
    }

    // Ancho y altura del nivel
    public double getAncho()
    {
        double ret = 0;
        for(Objeto objeto: _objetos)
            ret = Math.max(ret, objeto.getX() + objeto.getAncho());

        return ret;
    }
    public double getAltura()
    {
        double ret = 0;
        for(Objeto objeto: _objetos)
            ret = Math.max(ret, objeto.getY() + objeto.getAltura());

        return ret;
    }

    // Empresa y casita ubicadas en el punto especificado
    public Empresa getEmpresa(double x, double y)
    {
        for(Empresa empresa: _empresas) if( empresa.contiene(x,y) )
            return empresa;

        return null;
    }
    public Casita getCasita(double x, double y)
    {
        for(Casita casita: _casitas) if( casita.contiene(x,y) )
            return casita;

        return null;
    }

    // La primera empresa del tipo especificado
    public Empresa getEmpresa(Empresa.Tipo tipo)
    {
        for(Empresa empresa: _empresas) if( empresa.getTipo() == tipo )
            return empresa;

        return null;
    }

    // Informa si el nivel está terminado
    public boolean terminado()
    {
        for(Casita casita: _casitas) if( casita.completa() == false )
            return false;

        return true;
    }
}
