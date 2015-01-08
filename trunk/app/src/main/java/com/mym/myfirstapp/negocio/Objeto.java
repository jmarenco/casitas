package com.mym.myfirstapp.negocio;

public class Objeto
{
    // Posicion
    private double _x;
    private double _y;

    // Constructor
    public Objeto(double x, double y)
    {
        _x = x;
        _y = y;
    }

    // Getters
    public double getX()
    {
        return _x;
    }
    public double getY()
    {
        return _y;
    }
    public double getAncho() { return 60; }
    public double getAltura() { return 60; }

    // Determina si contiene al punto especificado
    public boolean contiene(double x, double y)
    {
        return getX() <= x && x <= getX() + getAncho() && getY() <= y && y <= getY() + getAltura();
    }
}
