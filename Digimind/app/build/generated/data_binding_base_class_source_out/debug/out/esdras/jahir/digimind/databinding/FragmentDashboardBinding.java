// Generated by view binder compiler. Do not edit!
package esdras.jahir.digimind.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import esdras.jahir.digimind.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDashboardBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final Button btnTime;

  @NonNull
  public final CheckBox checkFriday;

  @NonNull
  public final CheckBox checkMonday;

  @NonNull
  public final CheckBox checkSaturday;

  @NonNull
  public final CheckBox checkSunday;

  @NonNull
  public final CheckBox checkThursday;

  @NonNull
  public final CheckBox checkTuesday;

  @NonNull
  public final CheckBox checkWednesday;

  @NonNull
  public final EditText etTask;

  @NonNull
  public final ImageView textDashboard;

  private FragmentDashboardBinding(@NonNull LinearLayout rootView, @NonNull Button btnSave,
      @NonNull Button btnTime, @NonNull CheckBox checkFriday, @NonNull CheckBox checkMonday,
      @NonNull CheckBox checkSaturday, @NonNull CheckBox checkSunday,
      @NonNull CheckBox checkThursday, @NonNull CheckBox checkTuesday,
      @NonNull CheckBox checkWednesday, @NonNull EditText etTask,
      @NonNull ImageView textDashboard) {
    this.rootView = rootView;
    this.btnSave = btnSave;
    this.btnTime = btnTime;
    this.checkFriday = checkFriday;
    this.checkMonday = checkMonday;
    this.checkSaturday = checkSaturday;
    this.checkSunday = checkSunday;
    this.checkThursday = checkThursday;
    this.checkTuesday = checkTuesday;
    this.checkWednesday = checkWednesday;
    this.etTask = etTask;
    this.textDashboard = textDashboard;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_save;
      Button btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.btn_time;
      Button btnTime = ViewBindings.findChildViewById(rootView, id);
      if (btnTime == null) {
        break missingId;
      }

      id = R.id.checkFriday;
      CheckBox checkFriday = ViewBindings.findChildViewById(rootView, id);
      if (checkFriday == null) {
        break missingId;
      }

      id = R.id.checkMonday;
      CheckBox checkMonday = ViewBindings.findChildViewById(rootView, id);
      if (checkMonday == null) {
        break missingId;
      }

      id = R.id.checkSaturday;
      CheckBox checkSaturday = ViewBindings.findChildViewById(rootView, id);
      if (checkSaturday == null) {
        break missingId;
      }

      id = R.id.checkSunday;
      CheckBox checkSunday = ViewBindings.findChildViewById(rootView, id);
      if (checkSunday == null) {
        break missingId;
      }

      id = R.id.checkThursday;
      CheckBox checkThursday = ViewBindings.findChildViewById(rootView, id);
      if (checkThursday == null) {
        break missingId;
      }

      id = R.id.checkTuesday;
      CheckBox checkTuesday = ViewBindings.findChildViewById(rootView, id);
      if (checkTuesday == null) {
        break missingId;
      }

      id = R.id.checkWednesday;
      CheckBox checkWednesday = ViewBindings.findChildViewById(rootView, id);
      if (checkWednesday == null) {
        break missingId;
      }

      id = R.id.et_task;
      EditText etTask = ViewBindings.findChildViewById(rootView, id);
      if (etTask == null) {
        break missingId;
      }

      id = R.id.text_dashboard;
      ImageView textDashboard = ViewBindings.findChildViewById(rootView, id);
      if (textDashboard == null) {
        break missingId;
      }

      return new FragmentDashboardBinding((LinearLayout) rootView, btnSave, btnTime, checkFriday,
          checkMonday, checkSaturday, checkSunday, checkThursday, checkTuesday, checkWednesday,
          etTask, textDashboard);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
