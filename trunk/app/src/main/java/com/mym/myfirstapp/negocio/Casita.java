package com.mym.myfirstapp.negocio;

import com.mym.myfirstapp.R;

public class Casita extends Objeto
{
    // Servicios que requiere y servicios instalados
    private boolean[] _necesidades;
    private boolean[] _instalados;

    // Constructor
    public Casita(double x, double y)
    {
        super(R.drawable.casita, x, y);

        _necesidades = new boolean[Empresa.Tipo.values().length];
        _instalados = new boolean[Empresa.Tipo.values().length];
    }

    // Agrega y consulta un requerimiento de servicio
    public void setNecesidad(Empresa.Tipo servicio)
    {
        _necesidades[servicio.ordinal()] = true;
    }
    public boolean getNecesidad(Empresa.Tipo servicio)
    {
        return _necesidades[servicio.ordinal()];
    }

    // Agrega y consulta un servicio instalado
    public void setServicio(Empresa.Tipo servicio)
    {
        _instalados[servicio.ordinal()] = true;
        _necesidades[servicio.ordinal()] = false;
    }
    public boolean getServicio(Empresa.Tipo servicio)
    {
        return _instalados[servicio.ordinal()];
    }
}
