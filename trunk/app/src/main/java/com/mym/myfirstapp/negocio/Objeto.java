package com.mym.myfirstapp.negocio;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Objeto
{
    // ID de la imagen
    private int _id;

    // Posicion virtual y dimensiones reales de la imagen
    private double _x;
    private double _y;

    // Constructor
    public Objeto(int id, double x, double y)
    {
        _id = id;
        _x = x;
        _y = y;
    }

    // Getters
    public int getID()
    {
        return _id;
    }
    public double getX()
    {
        return _x;
    }
    public double getY()
    {
        return _y;
    }

    // Márgenes para los dibujos
    public static double margenX()
    {
        return 40;
    }
    public static double margenY()
    {
        return 40;
    }
}
