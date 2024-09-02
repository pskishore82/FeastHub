package psk.example.feasthub;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.feasthub.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Home extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.show(1, true);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.order));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.profile));

        navigateToFragment(new Mainpage());

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch(model.getId()) {
                    case 1:
                        // Launch the Mainpage (Home) activity when the Home bubble is clicked
                        navigateToFragment(new Mainpage());
                        break;
                    case 2:
                        // Launch the OrderActivity when the Order bubble is clicked
                        navigateToFragment(new OrderActivity());
                        break;
                    case 3:
                        // Launch the ProfileActivity when the Profile bubble is clicked
                        navigateToFragment(new ProfileActivity());
                        break;
                }
                return null;
            }
        });

    }

    private void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}
