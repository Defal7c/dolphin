// SPDX-License-Identifier: GPL-2.0-or-later

package org.dolphinemu.dolphinemu.features.settings.model;

import androidx.annotation.NonNull;

public class AdHocStringSetting implements AbstractStringSetting
{
  private final String mFile;
  private final String mSection;
  private final String mKey;
  private final String mDefaultValue;

  public AdHocStringSetting(String file, String section, String key, String defaultValue)
  {
    mFile = file;
    mSection = section;
    mKey = key;
    mDefaultValue = defaultValue;

    if (!NativeConfig.isSettingSaveable(file, section, key))
    {
      throw new IllegalArgumentException("File/section/key is unknown or legacy");
    }
  }

  @Override
  public boolean isOverridden()
  {
    return NativeConfig.isOverridden(mFile, mSection, mKey);
  }

  @Override
  public boolean isRuntimeEditable()
  {
    return true;
  }

  @Override
  public boolean delete(@NonNull Settings settings)
  {
    return NativeConfig.deleteKey(settings.getWriteLayer(), mFile, mSection, mKey);
  }

  @NonNull @Override
  public String getString()
  {
    return NativeConfig.getString(NativeConfig.LAYER_ACTIVE, mFile, mSection, mKey, mDefaultValue);
  }

  @Override
  public void setString(@NonNull Settings settings, @NonNull String newValue)
  {
    NativeConfig.setString(settings.getWriteLayer(), mFile, mSection, mKey, newValue);
  }

  public static String getStringGlobal(String file, String section, String key, String defaultValue)
  {
    return NativeConfig.getString(NativeConfig.LAYER_ACTIVE, file, section, key, defaultValue);
  }
}
