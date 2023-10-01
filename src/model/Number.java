package model;

public class Number {
    public double value;
    private MeasureType type;
    public Number(double value, MeasureType type1)
    {
        this.value = value;
        this.type = type1;
    }
    public Number(){
        this.value = 0;
        this.type = MeasureType.Метр;
    }
    public double getValue() {
        return value;
    }

    public Number lengthTo(MeasureType newType){
        double newValue = 0;
        if(this.type == MeasureType.Метр){
            switch (newType) {
                case Метр:
                    newValue = this.value;
                    break;

                case Миллиметр:
                    newValue = this.value * 1000;
                    break;

                case Сантиметр:
                    newValue = this.value * 100;
                    break;

                case Дециметр:
                    newValue = this.value * 10;
                    break;

                case Километр:
                    newValue = this.value / 1000;
                    break;

            }
        }
        else if(newType == MeasureType.Метр){
            switch (this.type){
                case Метр:
                    newValue = this.value;
                    break;

                case Миллиметр:
                    newValue = this.value / 1000;
                    break;

                case Сантиметр:
                    newValue = this.value / 100;
                    break;

                case Дециметр:
                    newValue = this.value / 10;
                    break;

                case Километр:
                    newValue = this.value * 1000;
                    break;
            }
        }
        else
        {
            newValue = this.lengthTo(MeasureType.Метр).lengthTo(newType).value;
        }
        return new Number(newValue, newType);
    }

    public Number weightTo (MeasureType newType) {
        double newValue = 0;
        if (this.type == MeasureType.Килограмм) {
            switch (newType) {
                case Килограмм:
                    newValue = this.value;
                    break;

                case Грамм:
                    newValue = this.value * 1000;
                    break;

                case Тонна:
                    newValue = this.value / 1000;
                    break;

                case Центер:
                    newValue = this.value / 100;
                    break;

            }
        }
        else if (newType == MeasureType.Килограмм) {
            switch (this.type) {
                case Килограмм:
                    newValue = this.value;
                    break;

                case Грамм:
                    newValue = this.value / 1000;
                    break;

                case Тонна:
                    newValue = this.value * 1000;
                    break;

                case Центер:
                    newValue = this.value * 100;
                    break;

            }
        }
        else
        {
            newValue = this.weightTo(MeasureType.Килограмм).weightTo(newType).value;
        }
        return new Number(newValue, newType);
    }

    public Number temperatureTo(MeasureType newType) {
        double newValue = 0;
        if (this.type == MeasureType.Кельвин) {
            switch (newType) {
                case Кельвин:
                    newValue = this.value;
                    break;

                case Цельсия:
                    newValue = this.value - 273.15 ;
                    break;

                case Фаренгейт:
                    newValue = (this.value - 273.15)* 9/5 + 32;
                    break;

            }
        }
        else if (newType == MeasureType.Кельвин) {
            switch (this.type) {
                case Кельвин:
                    newValue = this.value;
                    break;

                case Цельсия:
                    newValue = this.value + 273.15 ;
                    break;

                case Фаренгейт:
                    newValue = (this.value - 32)* 5/9 + 273.15;
                    break;

            }
        }
        else
        {
            newValue = this.temperatureTo(MeasureType.Кельвин).temperatureTo(newType).value;
        }
        return new Number(newValue, newType);
    }

    public Number timeTo(MeasureType newType) {
        double newValue = 0;
        if (this.type == MeasureType.Секунда) {
            switch (newType) {
                case Секунда:
                    newValue = this.value;
                    break;

                case Минута:
                    newValue = this.value / 60;
                    break;

                case Час:
                    newValue = this.value / 3600;
                    break;

                case День:
                    newValue = this.value / 86400 ;
                    break;
            }
        }
        else if (newType == MeasureType.Секунда) {
            switch (this.type) {
                case Секунда:
                    newValue = this.value;
                    break;

                case Минута:
                    newValue = this.value * 60;
                    break;

                case Час:
                    newValue = this.value * 3600;
                    break;

                case День:
                    newValue = this.value * 86400 ;
                    break;
            }
        }
        else
        {
            newValue = this.timeTo(MeasureType.Секунда).timeTo(newType).value;
        }
        return new Number(newValue, newType);
    }

    public Number pressureTo(MeasureType newType) {
        double newValue = 0;
        if (this.type == MeasureType.Паскаль) {
            switch (newType) {
                case Паскаль:
                    newValue = this.value;
                    break;

                case Килопаскаль:
                    newValue = this.value / 1000;
                    break;

                case Миллипаскаль:
                    newValue = this.value * 1000;
                    break;

                case Гектопаскаль:
                    newValue = this.value / 100 ;
                    break;
            }
        }
        else if (newType == MeasureType.Паскаль) {
            switch (this.type) {
                case Паскаль:
                    newValue = this.value;
                    break;

                case Килопаскаль:
                    newValue = this.value * 1000;
                    break;

                case Миллипаскаль:
                    newValue = this.value / 1000;
                    break;

                case Гектопаскаль:
                    newValue = this.value * 100;
                    break;

            }
        }
        else
        {
            newValue = this.pressureTo(MeasureType.Паскаль).pressureTo(newType).value;
        }
        return new Number(newValue, newType);
    }

    public Number speedTo(MeasureType newType) {
        double newValue = 0;
        if (this.type == MeasureType.Метров_в_Секунду) {
            switch (newType) {
                case Метров_в_Секунду:
                    newValue = this.value;
                    break;

                case Метров_в_Час:
                    newValue = this.value * 3600;
                    break;

                case Метров_в_Минуту:
                    newValue = this.value * 60;
                    break;

                case Километров_в_Минуту:
                    newValue = this.value * 60 / 1000;
                    break;

                case Километров_в_Час:
                    newValue = this.value * 3600 / 1000;
                    break;
            }
        }
        else if (newType == MeasureType.Метров_в_Секунду) {
            switch (this.type) {
                case Метров_в_Секунду:
                    newValue = this.value;
                    break;

                case Метров_в_Час:
                    newValue = this.value / 3600;
                    break;

                case Метров_в_Минуту:
                    newValue = this.value / 60;
                    break;

                case Километров_в_Минуту:
                    newValue = this.value / 60 * 1000;
                    break;

                case Километров_в_Час:
                    newValue = this.value / 3600 * 1000;
                    break;
            }
        }
        else
        {
            newValue = this.speedTo(MeasureType.Метров_в_Секунду).speedTo(newType).value;
        }
        return new Number(newValue, newType);
    }

}
