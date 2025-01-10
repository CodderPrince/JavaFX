package com.anuragroy.Models;

public class ImageHandler {

    public static String getImage(String icon) {
        switch (icon){
            case "01d":
                return "/images/01d.png";
            case "01n":
                return "/images/01n.png";
            case "02d":
                return "/images/02d.png";
            case "02n":
                return "/images/02n.png";
            case "03d": case "03n":
                return "/images/03.png";
            case "04d": case "04n":
                return "/images/04.png";
            case "09d": case "09n":
                return "/images/09.png";
            case "10d":
                return "/images/10d.png";
            case "10n":
                return "/images/10n.png";
            case "11n": case "11d":
                return "/images/11.png";
            case "13d": case "13n":
                return "/images/13.png";
            case "50d": case "50n":
                return "/images/50.png";
        }
        return "images/01d.jpg";
    }

}
