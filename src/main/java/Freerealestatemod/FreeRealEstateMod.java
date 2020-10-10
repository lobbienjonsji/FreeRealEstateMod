package Freerealestatemod;

import GifTheSpire.GifTheSpireLib;
import GifTheSpire.Subscribers.PostAnimationEnd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.characters.Defect;
import com.megacrit.cardcrawl.events.exordium.Sssserpent;
import GifTheSpire.util.GifAnimation;
import GifTheSpire.util.IDCheckDontTouchPls;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@SpireInitializer
public class FreeRealEstateMod implements
        PostInitializeSubscriber, PostDrawSubscriber, PostAnimationEnd {
    private static String modID;
    public static GifAnimation FreeRealEstate = new GifAnimation("FreeRealEstateResources/images/other/freerealestatepritesheet.png", 5, 17, 0, 0, 2.0F, 2.0F, false );
    public static GifAnimation Counter = new GifAnimation("FreeRealEstateResources/images/other/time.png", 4, 4, 0, 0, 2.0F, 2.0F, false, 3);
    public static GifAnimation Counter2 = new GifAnimation("FreeRealEstateResources/images/other/time.png", 4, 4, 0, 0, 0.5F, 0.5F, false, 3);
    public static GifAnimation Counter3 = new GifAnimation("FreeRealEstateResources/images/other/time.png", 4, 4, 0, 0, 2.0F, 2.0F, false, 3);
    public static GifAnimation Counter4 = new GifAnimation("FreeRealEstateResources/images/other/time.png", 4, 4, 0, 0, 2.0F, 2.0F, false);
    public FreeRealEstateMod() {
        BaseMod.subscribe(this);
        GifTheSpireLib.subscribe(this);
        setModID("FreeRealEstate");
    }
    //THIS PART IS IRRELEVANT DEFAULT MOD STUFF!!!!

    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStrings.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = FreeRealEstateMod.class.getResourceAsStream("/IDCheckStrings.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT

        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
    } // NO

    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH

    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStrings.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = FreeRealEstateMod.class.getResourceAsStream("/IDCheckStrings.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT

        String packageName = FreeRealEstateMod.class.getPackage().getName(); // STILL NOT EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    // ====== YOU CAN EDIT AGAIN ======

    //THIS IS WHERE IT GETS RELEVANT AGAIN

    @SuppressWarnings("unused")
    public static void initialize() {
        FreeRealEstateMod defaultmod = new FreeRealEstateMod();
    }
    @Override
    public void receivePostInitialize() {
        FreeRealEstate.create();
        FreeRealEstate.addAsBackgroundAnimation();
        FreeRealEstate.addAsCardAnimation("Strike_R");
        FreeRealEstate.addAsEventAnimation(Sssserpent.class.getName());
        FreeRealEstate.addAsCharacterAnimation(Defect.class.getName());

        Counter.create();
        Counter.addAsCardAnimation("Defend_R");
        Counter.setAnimationspeed(0.5f);

        Counter2.create();
        Counter2.addAsForeGroundAnimation();
        Counter2.setLoop(false);

        Counter3.create();
        Counter3.addAsCardAnimation("Bash");
        Counter3.setLoop(false);
        Counter3.setAnimationspeed(0.3f);

        Counter4.create();
        Counter4.addAsMonsterAnimation("Cultist");
        Counter4.setAnimationspeed(0.5f);
    }
    //ALSO IRRELEVANT
        public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        Counter2.playOnce();
        if(abstractCard instanceof Bash) {
            Counter3.ishidden = false;
            Counter3.playOnceOnSpecificCard(abstractCard);
        }
    }

    @Override
    public void PostAnimationEnd(GifAnimation gifAnimation) {
        if(gifAnimation == Counter3)
        {
            Counter3.ishidden = true;
        }
    }
}
