/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocnikovaprace;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 *
 * @author Mirek Sklenář
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button BtnUtok;
    @FXML
    private Button BtnObrana;
    @FXML
    private Button BtnHeal;
    @FXML
    private Button BtnUtek;
    @FXML
    private ProgressBar HPHrdinaBar;
    @FXML
    private ProgressBar HPMonstraBar;
    @FXML
    private TextArea StatsText;
    @FXML
    private Region PozadiStats;
    @FXML
    private Label MonstraJmeno;
    @FXML
    private Region PozadiNext;
    @FXML
    private ToggleButton BtnMonstStats;
    @FXML
    private ToggleButton BtnStats;
    @FXML
    private TextArea MonstrStatsText;
    @FXML
    private Button BtnNext;
    @FXML
    private Label NextExp;
    @FXML
    private Label NextLvlUp;
    @FXML
    private ImageView ImgSlime;
    @FXML
    private ImageView ImgKostlivec;
    @FXML
    private ImageView ImgZombie;

    String jmeno = "Test Slime";
    static int Locallvl = 1;
    int LocalExpGiv = Locallvl * 10;
    int LocalHP = 30;
    int LocalMaxHP = 30;
    int LocalAtk = 0;
    int LocalDef = 0;
    int LocalHeal = 0;

    @FXML
    private void Utok(ActionEvent event) {
        int kkk = Hrdina.atk - LocalDef;
        if (kkk <= 0) {
            kkk = 0;
        } else {
            LocalHP = LocalHP - kkk;
            double HPBar = (double) LocalHP / (double) LocalMaxHP;
            HPMonstraBar.setProgress(HPBar);
            Hrdina.HP = Hrdina.HP - LocalAtk;
            double k = (double) Hrdina.HP / (double) Hrdina.MaxHP;
            HPHrdinaBar.setProgress(k);
        }
        if (LocalHP <= 0) {
            Hrdina.exp = Hrdina.exp + LocalExpGiv;
            int levelup = Hrdina.exp / Hrdina.MaxExp;
            PozadiNext.setVisible(true);
            BtnNext.setVisible(true);
            NextExp.setVisible(true);
            NextExp.setText("Dostal jste " + LocalExpGiv + "XP");
            BtnUtek.setDisable(true);
            BtnUtok.setDisable(true);
            BtnObrana.setDisable(true);
            BtnHeal.setDisable(true);
            if (levelup >= 1) {
                NextLvlUp.setVisible(true);
                NextLvlUp.setText("Dosahl jste noveho levelu. Vas level je nyni: " + (Hrdina.lvl + 1));
            }
        }
        if (Hrdina.HP <= 0) {
            BtnHeal.setDisable(true);
            BtnUtok.setDisable(true);
            BtnObrana.setDisable(true);
            BtnUtek.setDisable(true);
            BtnMonstStats.setDisable(true);
            BtnStats.setDisable(true);

        }
    }

    @FXML
    private void Obrana(ActionEvent event) {
        int ll = LocalAtk - Hrdina.def;
        if (ll <= 0) {
            Hrdina.HP = Hrdina.HP;
            double k = (double) Hrdina.HP / (double) Hrdina.MaxHP;
            HPHrdinaBar.setProgress(k);
            LocalHP = LocalHP - 5;
            double HPBar = (double) LocalHP / (double) LocalMaxHP;
            HPMonstraBar.setProgress(HPBar);
        } else {
            Hrdina.HP = Hrdina.HP - (int) (ll / 4);
            double k = (double) Hrdina.HP / (double) Hrdina.MaxHP;
            HPHrdinaBar.setProgress(k);
            LocalHP--;
            double HPBar = (double) LocalHP / (double) LocalMaxHP;
            HPMonstraBar.setProgress(HPBar);
            
        }
        if(LocalHP <= 0){
            Hrdina.exp = Hrdina.exp + LocalExpGiv;
            int levelup = Hrdina.exp / Hrdina.MaxExp;
            PozadiNext.setVisible(true);
            BtnNext.setVisible(true);
            NextExp.setVisible(true);
            NextExp.setText("Dostal jste " + LocalExpGiv + "XP");
            BtnUtek.setDisable(true);
            BtnUtok.setDisable(true);
            BtnObrana.setDisable(true);
            BtnHeal.setDisable(true);
            if (levelup >= 1) {
                NextLvlUp.setVisible(true);
                NextLvlUp.setText("Dosahl jste noveho levelu. Vas level je nyni: " + (Hrdina.lvl + 1));
            }
        }
        if (Hrdina.HP <= 0) {
            BtnHeal.setDisable(true);
            BtnUtok.setDisable(true);
            BtnObrana.setDisable(true);
            BtnUtek.setDisable(true);
            BtnMonstStats.setDisable(true);
            BtnStats.setDisable(true);

        }
    }

    @FXML
    private void Heal(ActionEvent event) {
        int kk = (int) (Math.random() * 9);
        if (kk <= 4) {
            Hrdina.HP = Hrdina.HP + 10;
        }
        if (kk > 4 && kk <= 7) {
            Hrdina.HP = Hrdina.HP + 15;
        }
        if (kk > 7) {
            Hrdina.HP = Hrdina.HP + 35;
        }
        if (Hrdina.HP > Hrdina.MaxHP) {
            Hrdina.HP = Hrdina.MaxHP;
        }
        Hrdina.HP = Hrdina.HP - (LocalAtk / 2);
        double k = (double) Hrdina.HP / (double) Hrdina.MaxHP;
        HPHrdinaBar.setProgress(k);
        LocalHeal++;
        if (LocalHeal == 2) {
            BtnHeal.setDisable(true);
            if (Hrdina.HP <= 0) {
                BtnHeal.setDisable(true);
                BtnUtok.setDisable(true);
                BtnObrana.setDisable(true);
                BtnUtek.setDisable(true);
                BtnMonstStats.setDisable(true);
                BtnStats.setDisable(true);
            }
        }

    }

    @FXML
    private void Utek(ActionEvent event) {
        ImgSlime.setVisible(false);
        ImgKostlivec.setVisible(false);
        ImgZombie.setVisible(false);
        Hrdina.HP = Hrdina.MaxHP;
        int kk = (int) (Math.random() * 3);
        String jmeno1 = "";
        switch (kk) {
            case 0:
                jmeno1 = "Slime";
                Locallvl = (int) (Math.random() * 3 + Hrdina.lvl);
                ImgSlime.setVisible(true);
                break;
            case 1:
                jmeno1 = "Kostlivec";
                Locallvl = (int) (Math.random() * 3 + Hrdina.lvl * 2);
                ImgKostlivec.setVisible(true);
                break;
            case 2:
                jmeno1 = "Zombie";
                Locallvl = (int) (Math.random() * 4 + Hrdina.lvl);
                ImgZombie.setVisible(true);
                break;
        }
        jmeno = jmeno1;
        LocalExpGiv = Locallvl * 5;
        LocalHP = Locallvl * 10 + 20;
        LocalMaxHP = Locallvl * 10 + 20;
        LocalAtk = Locallvl * 2;
        LocalDef = Locallvl;
        double HPBar = (double) LocalHP / (double) LocalMaxHP;
        MonstraJmeno.setText(jmeno);
        HPMonstraBar.setProgress(HPBar);
        BtnHeal.setDisable(false);
        LocalHeal = 0;

    }

    @FXML
    private void Stats(ActionEvent event) {
        ToggleButton BtnStats = (ToggleButton) event.getSource();
        if (BtnStats.isSelected()) {

            StatsText.setText("Stats: \n" + "lvl:" + Hrdina.lvl + "\n" + "Exp: " + Hrdina.exp + "/" + Hrdina.MaxExp + "\n" + "HP: " + Hrdina.HP + "/" + Hrdina.MaxHP + "\n" + "Atk: " + Hrdina.atk + "\n" + "Def: " + Hrdina.def + "\n");
            StatsText.setVisible(true);

        } else {
            StatsText.setVisible(false);

        }
    }

    @FXML
    private void MonstraStats(ActionEvent event) {
        ToggleButton BtnMonstStats = (ToggleButton) event.getSource();
        if (BtnMonstStats.isSelected()) {

            MonstrStatsText.setText("Stats: \n" + "lvl:" + Locallvl + "\n" + "HP: " + LocalHP + "/" + LocalMaxHP + "\n" + "Atk: " + LocalAtk + "\n" + "Def: " + LocalDef + "\n");
            MonstrStatsText.setVisible(true);

        } else {
            MonstrStatsText.setVisible(false);

        }

    }

    @FXML
    private void Next(ActionEvent event) {
        ImgSlime.setVisible(false);
        ImgKostlivec.setVisible(false);
        ImgZombie.setVisible(false);

        int levelup = Hrdina.exp / Hrdina.MaxExp;
        if (levelup >= 1) {
            Hrdina.lvl = Hrdina.lvl + levelup;
            Hrdina.MaxExp = Hrdina.MaxExp + 100;
            Hrdina.atk = 5 * Hrdina.lvl;
            Hrdina.def = Hrdina.def + Hrdina.lvl;
            Hrdina.MaxHP = Hrdina.MaxHP + 5 * Hrdina.lvl;
            Hrdina.HP = Hrdina.MaxHP;

        }

        Hrdina.HP = Hrdina.MaxHP;
        int kk = (int) (Math.random() * 3);
        String jmeno1 = "";
        switch (kk) {
            case 0:
                jmeno1 = "Slime";
                Locallvl = (int) (Math.random() * 3 + Hrdina.lvl);
                ImgSlime.setVisible(true);
                break;
            case 1:
                jmeno1 = "Kostlivec";
                Locallvl = (int) (Math.random() * 3 + Hrdina.lvl * 2);
                ImgKostlivec.setVisible(true);
                break;
            case 2:
                jmeno1 = "Zombie";
                Locallvl = (int) (Math.random() * 4 + Hrdina.lvl);
                ImgZombie.setVisible(true);
                break;
        }
        jmeno = jmeno1;
        LocalExpGiv = Locallvl * 5;
        LocalHP = Locallvl * 10 + 20;
        LocalMaxHP = Locallvl * 10 + 20;
        LocalAtk = Locallvl * 2;
        LocalDef = Locallvl;
        double HPBar = (double) LocalHP / (double) LocalMaxHP;
        MonstraJmeno.setText(jmeno);
        HPMonstraBar.setProgress(HPBar);
        double k = (double) Hrdina.HP / (double) Hrdina.MaxHP;
        HPHrdinaBar.setProgress(k);
        PozadiNext.setVisible(false);
        BtnNext.setVisible(false);
        NextExp.setVisible(false);
        NextLvlUp.setVisible(false);
        BtnHeal.setDisable(false);
        BtnUtek.setDisable(false);
        BtnUtok.setDisable(false);
        BtnObrana.setDisable(false);
        BtnHeal.setDisable(false);
        LocalHeal = 0;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
