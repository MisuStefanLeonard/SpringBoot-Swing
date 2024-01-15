package com.dbapplication;


import com.dbapplication.service.CabineteDoctoriServiceImplementation;
import com.dbapplication.service.CabineteDoctoriViewServiceImplementation;
import com.dbapplication.service.DoctoriServiceImplementation;
import com.dbapplication.service.MedicamenteServiceImplementation;
import com.dbapplication.service.PacientiServiceImplementation;
import com.dbapplication.service.ReteteCuMedicamenteleImplementation;
import com.dbapplication.service.ReteteServiceImplementation;
import com.dbapplication.service.ReteteViewServiceImplementation;
import com.dbapplication.service.TesteLaboratorServiceImplementation;
import com.dbapplication.service.TestePacientiServiceImplementation;
import com.dbapplication.service.TipDoctoriServiceImplementation;
import com.dbapplication.ui.MainUi.MainFrame;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.UIManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    
	public static void main(String[] args) {
           
           SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);

           builder.headless(false);

           ConfigurableApplicationContext context = builder.run(args);
           
           TipDoctoriServiceImplementation tipDoctoriServiceImplementation = context.getBean(TipDoctoriServiceImplementation.class);
           DoctoriServiceImplementation doctoriServiceImplementation = context.getBean(DoctoriServiceImplementation.class);
           PacientiServiceImplementation pacientiServiceImplementation = context.getBean(PacientiServiceImplementation.class);
           TesteLaboratorServiceImplementation testeLaboratorServiceImplementation = context.getBean(TesteLaboratorServiceImplementation.class);
           TestePacientiServiceImplementation testePacientiServiceImplementation = context.getBean(TestePacientiServiceImplementation.class);
           CabineteDoctoriServiceImplementation cabineteDoctoriServiceImplementation = context.getBean(CabineteDoctoriServiceImplementation.class);
           ReteteServiceImplementation reteteServiceImplementation = context.getBean(ReteteServiceImplementation.class);
           MedicamenteServiceImplementation medicamenteServiceImplementation = context.getBean(MedicamenteServiceImplementation.class);
           ReteteCuMedicamenteleImplementation reteteCuMedicamenteleImplementation = context.getBean(ReteteCuMedicamenteleImplementation.class);
           CabineteDoctoriViewServiceImplementation cabineteDoctoriViewServiceImplementation = context.getBean(CabineteDoctoriViewServiceImplementation.class);
           ReteteViewServiceImplementation reteteViewServiceImplementation = context.getBean(ReteteViewServiceImplementation.class);
           try {
               UIManager.setLookAndFeel(new FlatDarculaLaf());
           }catch (Exception e) {
               e.printStackTrace();
           }

           /* Create and display the form */
           java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                   new MainFrame(tipDoctoriServiceImplementation,doctoriServiceImplementation 
                       , pacientiServiceImplementation , testeLaboratorServiceImplementation,testePacientiServiceImplementation,
                       cabineteDoctoriServiceImplementation,reteteServiceImplementation , medicamenteServiceImplementation ,
                       reteteCuMedicamenteleImplementation , cabineteDoctoriViewServiceImplementation , reteteViewServiceImplementation)
                       .setVisible(true);
               }
           });
            
	}

}
