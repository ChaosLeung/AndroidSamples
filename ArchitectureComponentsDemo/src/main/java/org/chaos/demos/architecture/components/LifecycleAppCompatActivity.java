package org.chaos.demos.architecture.components;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Chaos
 *         27/05/2017
 */

public class LifecycleAppCompatActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}
