package com.brainstormideas.beer;

class Contador {

    private static int cantidadCervezas=0;
    private static double precioCervezas=0.0;

    Contador(){

    }

    public static int getCantidadCervezas() {
        return cantidadCervezas;
    }

    public  static void setCantidadCervezas(int cantidadCervezas) {
        Contador.cantidadCervezas = cantidadCervezas;
    }

    public static double getPrecioCervezas() {
        return precioCervezas;
    }

    public void setPrecioCervezas(double precioCervezas) {
        Contador.precioCervezas = precioCervezas;
    }

    public void incrementar(){
        cantidadCervezas++;
    }
    public void decrementar(){
        cantidadCervezas--;
    }
    public void reiniciar(){
        setCantidadCervezas(0);
    }
    public String pago(){
        double pago = getCantidadCervezas()*(Double)getPrecioCervezas();
        String pagofinal = String.valueOf(pago);
        return pagofinal;
    }
}
