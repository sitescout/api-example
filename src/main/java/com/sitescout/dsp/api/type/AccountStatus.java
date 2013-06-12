package com.sitescout.dsp.api.type;

public enum AccountStatus {
    unverified,      // requires e-mail verification or manual activation
    enabled,         // account verified via e-mail
    unverified_reset,// user has reset his/her password. must be changed upon login
    enabled_reset,   // user has reset his/her password. must be changed upon login
    disabled         // blocked
}
