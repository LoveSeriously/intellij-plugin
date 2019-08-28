package com.cloudting.intellij.plugin.core.config;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

/**
 * @author lw
 * @date 2019-08-27
 */
public class Bundle {
    /**
     * The {@link ResourceBundle} path.
     */
    @NonNls
    protected static final String BUNDLE_NAME = "config.bundle";

    /**
     * The {@link ResourceBundle} instance.
     *
     * @see #BUNDLE_NAME
     */
    protected static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Bundle() {
    }

    /**
     * Load a {@link String} from the {@link #BUNDLE} {@link ResourceBundle}.
     *
     * @param key    the key of the resource.
     * @param params the optional parameters for the specific resource.
     * @return the {@link String} value or {@code null} if no resource found for the key.
     */
    public static String message(@PropertyKey(resourceBundle = BUNDLE_NAME) String key, Object... params) {
        return CommonBundle.message(BUNDLE, key, params);
    }

    /**
     * Load a {@link String} from the {@link #BUNDLE} {@link ResourceBundle}.
     *
     * @param key    the key of the resource.
     * @param params the optional parameters for the specific resource.
     * @return the {@link String} value or an empty {@link String} if no resource found for the key.
     */
    public static String messageOrBlank(@PropertyKey(resourceBundle = BUNDLE_NAME) String key, Object... params) {
        return CommonBundle.messageOrDefault(BUNDLE, key, "test:foo", params);
    }

}
