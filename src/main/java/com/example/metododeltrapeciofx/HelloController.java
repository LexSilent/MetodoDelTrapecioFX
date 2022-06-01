package com.example.metododeltrapeciofx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Locale;

public class HelloController {
    @FXML private VBox vbCoeficiente, vbF,vbFinal, vbN, vbZ;
    @FXML Label[][] labels;
    @FXML TextField tfA,tfB,tfN,tfFuncion;
    double a=0,b=0,n=0,h,coeficiente=0,Final=0;
    @FXML Label lblResultado;
    @FXML RadioButton rbTrapecio,rbSimpson;

    @FXML protected void initialize()  {

        tfFuncion.setOnKeyPressed(keyEvent -> {//Codigo para salto de TXTBOX
            if(keyEvent.getCode()== KeyCode.ENTER){
                tfA.requestFocus();
            }
        });
        tfA.setOnKeyPressed(keyEvent -> {//Codigo para salto de TXTBOX
         if(keyEvent.getCode()== KeyCode.ENTER){
            tfB.requestFocus();
            }
        });
        tfB.setOnKeyPressed(keyEvent -> {//Codigo para salto de TXTBOX
            if(keyEvent.getCode()== KeyCode.ENTER){
                tfN.requestFocus();
            }
        });
        tfN.setOnKeyPressed(keyEvent -> {//Codigo para salto de TXTBOX
            if(keyEvent.getCode()== KeyCode.ENTER){
                iniciar();
            }
        });
    }

    @FXML void iniciar() {
        if(rbSimpson.selectedProperty().getValue()){
            Simpson();
        }else {
            Trapecio();
        }
    }
    public void Simpson(){
        Final=0;
        boolean Cuatro=true;

        a=Double.parseDouble(tfA.getText());
        b=Double.parseDouble(tfB.getText());
        n=Double.parseDouble(tfN.getText());

        vbCoeficiente.getChildren().clear();
        vbF.getChildren().clear();
        vbZ.getChildren().clear();
        vbFinal.getChildren().clear();
        vbN.getChildren().clear();

        labels=new Label[5][(int) n+2];
        agregarLabels(0,0,vbN,"n");
        agregarLabels(1,0,vbZ,"a+(h*n)");
        agregarLabels(2,0,vbF,"f(a+[h*n])");
        agregarLabels(3,0,vbCoeficiente,"Coeficiente");
        agregarLabels(4,0,vbFinal,"f(a+[h*n])*Coef.");

        h=(b-a)/n;

        for (int i=0;i<=n;i++){
            agregarLabels(0,i+1,vbN,String.valueOf(i));//n
            double z=a+(h*i);
            agregarLabels(1,i+1,vbZ,String.valueOf(z));//z
            Expression e = new ExpressionBuilder(tfFuncion.getText().toLowerCase(Locale.ROOT))
                    .variables("x")
                    .build()
                    .setVariable("x", z);
            double f = e.evaluate();

            agregarLabels(2,i+1,vbF,String.valueOf(f));//f
            if(i==0||i==n){
                coeficiente=1;
            }else if(Cuatro){
                coeficiente=4;
                Cuatro=!Cuatro;
            }else {
                coeficiente=2;
                Cuatro=!Cuatro;
            }
            agregarLabels(3,i,vbCoeficiente,String.valueOf(coeficiente));//Coeficiente
            Final=Final+(f*coeficiente);
            System.out.println(Final);
            agregarLabels(4,i,vbFinal,String.valueOf(f*coeficiente)); //Final
        }
        double sumatoria=Final*(h/3);
        lblResultado.setText(String.valueOf(sumatoria));
    }

    public void Trapecio(){
        Final=0;

        a=Double.parseDouble(tfA.getText());
        b=Double.parseDouble(tfB.getText());
        n=Double.parseDouble(tfN.getText());

        vbCoeficiente.getChildren().clear();
        vbF.getChildren().clear();
        vbZ.getChildren().clear();
        vbFinal.getChildren().clear();
        vbN.getChildren().clear();

        labels=new Label[5][(int) n+2];
        agregarLabels(0,0,vbN,"n");
        agregarLabels(1,0,vbZ,"a+(h*n)");
        agregarLabels(2,0,vbF,"f(a+[h*n])");
        agregarLabels(3,0,vbCoeficiente,"Coeficiente");
        agregarLabels(4,0,vbFinal,"f(a+[h*n])*Coef.");

        h=(b-a)/n;

        for (int i=0;i<=n;i++){
            agregarLabels(0,i+1,vbN,String.valueOf(i));//n
            double z=a+(h*i);
            agregarLabels(1,i+1,vbZ,String.valueOf(z));//z
            Expression e = new ExpressionBuilder(tfFuncion.getText().toLowerCase(Locale.ROOT))
                    .variables("x")
                    .build()
                    .setVariable("x", z);
            double f = e.evaluate();
            agregarLabels(2,i+1,vbF,String.valueOf(f));//f
            if(i==0||i==n){
                coeficiente=1;
            }else{
                coeficiente=2;
            }
            agregarLabels(3,i,vbCoeficiente,String.valueOf(coeficiente));//Coeficiente
            Final=Final+(f*coeficiente);
            agregarLabels(4,i,vbFinal,String.valueOf(f*coeficiente)); //Final
        }
        double sumatoria=Final*(h/2);
        lblResultado.setText(String.valueOf(sumatoria));

    }
    public void agregarLabels(int x,int i, VBox vBox,String dato){
        labels[x][i]=new Label();
        labels[x][i].setText(dato);
        vBox.getChildren().add(labels[x][i]);

    }
}