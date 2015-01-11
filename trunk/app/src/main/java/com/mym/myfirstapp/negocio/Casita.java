package com.mym.myfirstapp.negocio;

import com.mym.myfirstapp.R;

import java.util.ArrayList;

public class Casita extends Objeto
{
    // Servicios que requiere y servicios instalados
    private ArrayList<Empresa.Tipo> _necesidades;
    private ArrayList<Boolean> _instalados;

    // Constructor
    public Casita(double x, double y)
    {
        super(R.drawable.casita, x, y);

        _necesidades = new ArrayList<Empresa.Tipo>();
        _instalados = new ArrayList<Boolean>();
    }

    // Getters de servicios
    public ArrayList<Empresa.Tipo> getNecesidades()
    {
        return _necesidades;
    }

    // Agrega y consulta un requerimiento de servicio
    public void setNecesidad(Empresa.Tipo servicio)
    {
        if( _necesidades.contains(servicio) == false )
        {
            _necesidades.add(servicio);
            _instalados.add(false);
        }
    }
    public boolean getNecesidad(Empresa.Tipo servicio)
    {
        if( _necesidades.contains(servicio) == false )
            return false;

        int indice = _necesidades.indexOf(servicio);
        return !_instalados.get(indice);
    }

    // Agrega y consulta un servicio instalado
    public void setServicio(Empresa.Tipo servicio)
    {
        if( _necesidades.contains(servicio) )
        {
            int indice = _necesidades.indexOf(servicio);
            _instalados.set(indice, true);
        }
    }
    public boolean getServicio(Empresa.Tipo servicio)
    {
        if( _necesidades.contains(servicio) == false )
            return false;

        int indice = _necesidades.indexOf(servicio);
        return _instalados.get(indice);
    }

    // Informa si todas las necesidades están cubiertas
    public boolean completa()
    {
        for(Boolean servicio: _instalados) if( servicio == false )
            return false;

        return true;
    }
}
