package com.app.marvel;


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@LargeTest
@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

    @Mock
    Context mMockContext;

    @Test
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void utilsTest() {
        Drawable icon = com.app.marvel.utils.Utils.setIconColor(mMockContext, R.color.colorPrimary, R.drawable.ic_replay_black_24dp);

        assertNotNull(icon.getColorFilter());
    }


}
