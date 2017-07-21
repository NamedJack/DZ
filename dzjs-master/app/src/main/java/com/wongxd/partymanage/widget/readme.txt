 app:funcType="canClear"   清除文本功能

  app:funcType="canWatchPwd" 密码查看功能

####右侧图标点击事件#### PowerfulEditText同样支持右侧图片的点击事件，如果funcType指定为canClear，则默认点击是清除文本。如果需要进行一些额外的操作，则可以设置回调，比如搜索输入框，右侧是一个搜索的按钮，需要为其设置点击事件的回调。

布局文件：

 <com.chaychan.viewlib.PowerfulEditText
    android:id="@+id/pet"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:drawableRight="@mipmap/search"
    />
Activity

  PowerfulEditText petUsername = (PowerfulEditText) findViewById(R.id.pet);
    petUsername.setOnRightClickListener(new PowerfulEditText.OnRightClickListener() {
        @Override
        public void onClick(EditText editText) {
            String content = editText.getText().toString().trim();
            if (！TextUtils.isEmpty(content)){
                Toast.makeText(MainActivity.this, "执行搜索逻辑", Toast.LENGTH_SHORT).show();
            }
        }
    });