package com.mym.myfirstapp.negocio;

import com.mym.myfirstapp.R;

public class Empresa extends Objeto
{
    // Tipo de las empresas
    public enum Tipo { Luz, Gas, Telefono };

    // Tipo de la empresa
    private Tipo _tipo;

    // Id de las imágenes del servicio activo e inactivo
    private int _idActivo;
    private int _idInactivo;

    // Constructor
    public Empresa(Tipo tipo, double x, double y)
    {
        super(R.drawable.industria60, x, y);
        _tipo = tipo;

        switch(tipo)
        {
            case Luz: _idActivo = R.drawable.luz20; _idInactivo = R.drawable.luz20off; break;
            case Gas: _idActivo = R.drawable.gas20; _idInactivo = R.drawable.gas20off; break;
            case Telefono: _idActivo = R.drawable.telefono20; _idInactivo = R.drawable.telefono20off; break;
        }
    }

    // Getters
    public Tipo getTipo()
    {
        return _tipo;
    }
    public int getIdActivo()
    {
        return _idActivo;
    }
    public int getIdInactivo()
    {
        return _idInactivo;
    }
}
