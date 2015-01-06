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
}
