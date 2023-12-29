//package com.example.tridentapplication.util;
//
//
//import android.app.ActivityManager;
//import android.content.ComponentName;
//import android.content.Context;
//import androidx.annotation.NonNull;
//import androidx.work.Worker;
//import androidx.work.WorkerParameters;
//import java.util.List;
//
//public class BackgroundExecutors extends Worker {
//    public BackgroundExecutors(@NonNull Context context, @NonNull WorkerParameters workerParams) {
//        super(context, workerParams);
//    }
//
//    @Override
//    public Result doWork() {
//        try {
//            Context context = getApplicationContext();
//            Boolean isAppRunning = isAppRunning(context, context.getPackageName());
//            Boolean isAppKilledOrDestroyed = isAppKilledOrDestroyed(context);
//            Boolean isAppInForeground = isAppForeGround(context);
//            Boolean isAppBackground = isAppInBackGround(context);
//
//            if (isAppRunning || isAppInForeground || isAppKilledOrDestroyed || isAppBackground) {
//                // Todo do something
//            }
//
//            return Result.success();
//        } catch (Exception e) {
//            return Result.failure();
//        }
//
//    }
//
//
//    public static boolean isAppRunning(Context context, String packageName) {
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
//
//        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcesses) {
//            if (processInfo.processName.equals(packageName)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    private static boolean isAppKilledOrDestroyed(Context context) {
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> processes = activityManager.getRunningAppProcesses();
//
//        for (ActivityManager.RunningAppProcessInfo processInfo : processes) {
//            if (processInfo.processName.equals(context.getPackageName())) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private static boolean isAppInBackGround(Context context) {
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> processes = activityManager.getRunningAppProcesses();
//
//        if (processes != null) {
//            for (ActivityManager.RunningAppProcessInfo processInfo : processes) {
//                if (processInfo.processName == context.getPackageName()) {
//                    return processInfo.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
//                }
//            }
//        }
//        return false;
//    }
//
//    private static boolean isAppForeGround(Context context) {
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        if (activityManager != null) {
//            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
//
//            if (runningTasks != null && !runningTasks.isEmpty()) {
//                ComponentName topActivity = runningTasks.get(0).topActivity;
//                if (topActivity != null && topActivity.getPackageName().equals(context.getPackageName())) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}
