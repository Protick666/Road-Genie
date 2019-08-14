package com.example.protick.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;




public class Result extends AppCompatActivity {
    TextView tx;
     private int j=1,v1,v2;
    double cos=0.0,tims=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        tx=(TextView)findViewById(R.id.textView22);
        String str=new String();
        str+= "Your Optimal Route is: \n";
        int prev=-1;

        for(int i=2;i<MapsActivity2.desp.size();i=i+2)
        {
            v1=MapsActivity2.desp.get(i-2);
            v2=MapsActivity2.desp.get(i);
            cos+=MapsActivity2.edgemap.get(v1*100+v2)*MapsActivity2.pkm.get(MapsActivity2.desp.get(i-1));
            tims+=MapsActivity2.edgemap.get(v1*100+v2)*MapsActivity2.vel.get(MapsActivity2.desp.get(i-1));
            str+=Integer.toString(j)+". "+MapsActivity.Bus_Stops.get(MapsActivity2.desp.get(i-2))+ "->" + MapsActivity.Bus_Stops.get(MapsActivity2.desp.get(i))+" using Bus Service "+ MapsActivity2.desp.get(i-1) ;
            if(prev!=-1 && prev!=MapsActivity2.desp.get(i-1) && i!= MapsActivity2.desp.size()-1)
            {
                str+="(Change Bus Here)";
            }
            j++;
            str+="\n";
            prev=MapsActivity2.desp.get(i-1);

        }
        str+="\n";
        if(FromToPage.modv==1)
        {//" Taka.";
            str+= "Estimated Travel Time(Optimal Choice): "+ Math.round(MapsActivity2.dps.get(FromToPage.destv))+" Minutes.\n";
            str+= "Estimated Travel Cost : "+Math.round(cos)+" Taka.";
        }
        else if(FromToPage.modv==2)
        {
            str+= "Estimated Travel Cost(Optimal Choice): "+ Math.round(MapsActivity2.dps.get(FromToPage.destv))+" Taka.";
        }
        else if(FromToPage.modv==3)
        {
            //str+= "Estimated Travel Time(Optimal Choice): "+Math.round(tims)+" Minutes.\n";
            str+= "Estimated Travel Cost(Optimal Choice): "+Math.round(cos)+ " Taka.";

        }
        System.out.println("abde"+str);
        tx.setText(str);

    }
}
