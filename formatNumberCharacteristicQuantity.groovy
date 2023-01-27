import com.sap.it.api.mapping.*;
import java.util.HashMap;
import java.text.DecimalFormat;

def String formatNumberCharacteristicQuantity(String TypeCode, String UnitCode, String Quantity){

//define inpout value as variable
    def CharacteristicQuantityTypeCode = TypeCode as String;
    def CharacteristicQuantityUnitCode = UnitCode as String;
    def CharacteristicQuantity = Quantity as String;

    //define output value
    def ConvertedValue = 0 as Number;
    //define output number format
    def DecimalPattern = new DecimalFormat('#0.000');

 
    if (!CharacteristicQuantity.isEmpty()) {

        //change quantity in number type for operating
        double CharacteristicQuantityAsNumber = Double.parseDouble(CharacteristicQuantity);

        if(CharacteristicQuantityTypeCode == "NET_WT"){
            switch(CharacteristicQuantityUnitCode) { 
                //gramm
                case "GRM": 
                ConvertedValue = CharacteristicQuantityAsNumber / 1000;
                break;
                //tonn
                case "GT": 
                ConvertedValue = CharacteristicQuantityAsNumber / 0.001;
                break;
                //ounce
                case "ONZ": 
                ConvertedValue = CharacteristicQuantityAsNumber / 35.274;
                break;
                //pound
                case "LBR": 
                ConvertedValue = CharacteristicQuantityAsNumber / 2.204625002841;
                break;
                //kilogramm (wanted format)
                case "KGM": 
                ConvertedValue = CharacteristicQuantityAsNumber;
                break;
                default:
                ConvertedValue;
                } 
        }else if (CharacteristicQuantityTypeCode == "DIM_LENGTH" || CharacteristicQuantityTypeCode == "HEIGHT" || CharacteristicQuantityTypeCode == "BREADTH" ){
            switch(CharacteristicQuantityUnitCode) { 
                //inch
                case "INH": 
                ConvertedValue = CharacteristicQuantityAsNumber / 39.3701;
                break;
                //kimometer
                case "KMT": 
                ConvertedValue = CharacteristicQuantityAsNumber / 0.001;
                break;
                //miles
                case "SMI": 
                ConvertedValue = CharacteristicQuantityAsNumber / 0.000621371;
                break;
                //foot
                case "FOT": 
                ConvertedValue = CharacteristicQuantityAsNumber / 3.2808388799999997;
                break;
                //yard
                case "YRD": 
                ConvertedValue = CharacteristicQuantityAsNumber / 1.09361;
                break;
                //metre (wanted format)
                case "MTR": 
                ConvertedValue = CharacteristicQuantityAsNumber;
                break;
                default:
                ConvertedValue;
             } 
        }
    }
    
    //update output format with 3 decimals numbers
    CharacteristicQuantity = (DecimalPattern.format(ConvertedValue)).toString();

	return CharacteristicQuantity;
}