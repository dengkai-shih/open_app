# open_app
## Open Another Application
 使用 "Intent" 開啟在裝置內其他的 app
 1. 若是透過 Package Name 與 Class Name 的方式
 Intent activityIntent = new Intent();
 activityIntent.setComponent(new ComponentName("Package Name","Class Name"));
 startActivity(activityIntent);