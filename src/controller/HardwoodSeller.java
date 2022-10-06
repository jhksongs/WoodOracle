package controller;

//import com.aspose.words.*;
import model.WoodItem;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Jaan Harrison Kohm
 *
 */
public class HardwoodSeller {

    private static File file;
    WoodItem cherry;
    WoodItem curlyMaple;
    WoodItem genMahogany;
    WoodItem wenge;
    WoodItem whiteOak;
    WoodItem sawDust;
    private static int id;
    private static String name;
    private static String address;
    private static String date;



    public static void main(String[] args) throws IOException {
        FileWriter myWriter;

        //create new file
        try {
            myWriter = new FileWriter("invoice.txt");
            //myWriter.write("Files in Java might be tricky, but it is fun enough!");



        // TODO Auto-generated method stub
         Random idGen = new Random(999999);
        id = idGen.nextInt() + 1000000;
        //System.out.println("Invoive Number: " + id);
        myWriter.write("Invoive Number: " + id + "\n");
         HardwoodSeller seller = new HardwoodSeller();
         seller.setItems();
         seller.readInputFile("/Users/jaankohm/Documents/Clone/WoodOracle/orderExample");

        Scanner myReader = new Scanner(file);
        name = "";
        address = "";
        date = "";


        int count = 0;


        ArrayList<Double> itemsDeliverysTimes = new ArrayList<>();
        name = myReader.nextLine();
        myWriter.write(name+"\n");
        address = myReader.nextLine();
        myWriter.write(address+"\n");

        date = myReader.nextLine()+"\n";
        myWriter.write(date+"\n");
        //seller.printInvoice();
        String type = null;
        int amount = 0;
        while (myReader.hasNextLine()) {

                if(myReader.hasNextLine()) {
                    type = myReader.nextLine();
                    //System.out.println("Type of wood: " + type);
                    myWriter.write("\nType of wood: " + type);

                }
                if(myReader.hasNextInt()){
                    amount = myReader.nextInt();
                    System.out.println("Quantity: " + amount + "BF");
                    myWriter.write("\nQuantity: " + amount + "BF");
                    if(myReader.hasNextLine()) {
                        myReader.nextLine();
                    }
                }

                //System.out.println(seller.deliveryTime(seller.assignWoodItem(type), amount));
                itemsDeliverysTimes.add(seller.deliveryTime(seller.assignWoodItem(type), amount));


        }
        myReader.close();


        //System.out.println("\n\nMax delivery time: " + Collections.max(itemsDeliverysTimes));
        myWriter.write("\n\nMax delivery time: " + Collections.max(itemsDeliverysTimes));


            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

//        // Load source file TXT for conversion
//        Converter converter = new Converter("input.txt");
//// Prepare conversion options for target format PDF
//        ConvertOptions convertOptions = new FileType().fromExtension("pdf").getConvertOptions();
//// Convert to PDF format
//        converter.convert("output.pdf", convertOptions);

    }



    /**
    * Method for reading the input file to be processed by the Hardwood Seller
     **/
    public void readInputFile(String inputFilePath) throws FileNotFoundException {
        file = new File(inputFilePath);
    }


    /**
     * Method that sets items and attributes
     **/
    public void setItems(){
        cherry = new WoodItem("Cherry",2.5,5.95);
        curlyMaple = new WoodItem("Curly Maple",1.5,6.0);
        genMahogany = new WoodItem("Genuine Mahogany",3.0,9.6);
        wenge = new WoodItem("Wenge",5.0,22.35);
        whiteOak = new WoodItem("White Oak",2.3,6.7);
        sawDust = new WoodItem("Sawdust",1.0,1.5);

    }
    /**
     * Method that computes the delivery ETA per individual item
     **/
    public Double deliveryTime(WoodItem item, int bfAmount){
        return item.getDaseDeliveryTime()*bfCalc(bfAmount);
    }

    public double bfCalc(int bfAmount){
        if (bfAmount<=100)return 1.0;
        if (bfAmount<=200)return 2.0;
        if (bfAmount<=300)return 3.0;
        if (bfAmount<=400)return 4.0;
        if (bfAmount<=500)return 5.0;
        return 5.5;
    }


    public WoodItem assignWoodItem( String type){
        if(type.equals(cherry.getType()))return cherry;
        if(type.equals(curlyMaple.getType()))return curlyMaple;
        if(type.equals(genMahogany.getType()))return genMahogany;
        if(type.equals(wenge.getType()))return wenge;
        if(type.equals(whiteOak.getType()))return whiteOak;
        return sawDust;


    }


    public void printInvoice(){
        System.out.println(name);
        System.out.println(address);
        System.out.println(date);

    }


}