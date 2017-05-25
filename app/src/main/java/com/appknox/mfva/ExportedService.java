package com.appknox.mfva;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ExportedService extends Service {
    public ExportedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
