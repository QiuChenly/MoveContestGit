# MoveContestGit
---

* 本项目中所有的数据都是`假数据`，等到将所有的页面完成，将`假数据`换成`服务器中数据`。
---
## 改错题
---
### 第1题：	启动智能交通APP后，进入引导界面
---

* 完成情况:已完成

---

* 思路:

	* 将登录次数保存到`SharedPreferences`中，每次启动APP从其中取值，如果取出的值是`true`代表是第一启动APP，跳转到`启动页面`，`false`则直接显示`主界面`。

* 主要代码:
```java
    /**
     * 创建存储对象,使用SharedPreferences进行存储数据
     */
    private void createStorage() {

        // 创建SharedPreferences对象
        mSharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);

        // 创建SharedPreferences.Editor对象
        mEditor = mSharedPreferences.edit();
    }


    /**
     * 判断是否是第一次启动
     *
     * @return true：第一次启动，false：不是第一次启动
     */
    private void isFirstStartUp() {

        createStorage();

        mIsFirstStartUpBoolean = mSharedPreferences.getBoolean("isFirstStartUpBoolean", true);

        if (mIsFirstStartUpBoolean == true) {
            mEditor.putBoolean("isFirstStartUpBoolean", false);
            mEditor.apply();
            // 跳转到第一次启动页面
            startActivity(new Intent(LoginActivity.this, SplashActivity.class));
        }

    }
```

### 第2题：	启动移动端APP，进入登录界面
---

* 完成情况:已完成

* 思路:

	* 按照已给定的设计图，将Button、EditText、TextView进行重新的排序。
---

### 第3题：	移动端APP登录界面

* 完成情况:已完成

* 思路：

	* 1.登录部分：
	
		* 获取EditText控件的值，将`输入的用户名、密码`和`默认的用户名和密码`匹配，如果匹配成功，进入主页面，否则显示`用户名或密码错误！`。

	* 2.记住密码和自动登录:

		* 先判断`SharedPreferences中的记住密码和自动登录的`CheckBox的值`，如果true，显示主页面，false，登录页面。

* 代码：

	* 判断逻辑：
	
```java
		mAutoLoginBoolean = mSharedPreferences.getBoolean("autoLoginBoolean", false);
        mRemPwdBoolean = mSharedPreferences.getBoolean("remPwdBoolean", false);
        String userName = mSharedPreferences.getString("UserName", "");
        String passWord = mSharedPreferences.getString("PassWord", "");

        if(mRemPwdBoolean){
            mUserNameEdit.setText(userName);
            mPassWordEdit.setText(passWord);
            mRemPwdCheck.setChecked(mRemPwdBoolean);
            mAutoLoginCheck.setChecked(mAutoLoginBoolean);
        }else{
            mUserNameEdit.setText("");
            mPassWordEdit.setText("");
        }

        if(mAutoLoginBoolean){

            // 跳转到主页面
            if("admin".equals(userName)){
                // 跳转到管理员页面

            }else{
                // 跳转到用户页面
                Toast.makeText(this, "用户界面", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                finish();
            }

        }
```
	
```java
 /**
     * 判断登录是否成功
     *
     * @return true：成功   false：失败
     */
    private boolean isLoginSuccess(){
        mUserNameStr = mUserNameEdit.getText().toString().trim();
        mPassWordStr = mPassWordEdit.getText().toString().trim();
        String[] userNames = User.UserNames;
        for (int i = 0; i < userNames.length; i++) {
            Log.d(TAG, mUserNameStr + mPassWordStr);
            if (userNames[i].equals(mUserNameStr) && "123456".equals(mPassWordStr)) {
                mEditor.putBoolean("remPwdBoolean",mRemPwdCheck.isChecked());
                mEditor.putBoolean("autoLoginBoolean",mAutoLoginCheck.isChecked());
                mEditor.putString("UserName", mUserNameStr);
                mEditor.putString("PassWord", mPassWordStr);
                mEditor.apply();
                return true;
            } else if (mUserNameStr.equals("admin") && mPassWordStr.equals("admin")) {
                mEditor.putBoolean("remPwdBoolean",mRemPwdCheck.isChecked());
                mEditor.putBoolean("autoLoginBoolean",mAutoLoginCheck.isChecked());
                mEditor.putString("UserName", mUserNameStr);
                mEditor.putString("PassWord", mPassWordStr);
                mEditor.apply();
                return true;
            } else {
                mEditor.putBoolean("remPwdBoolean",false);
                mEditor.putBoolean("autoLoginBoolean",false);
                mEditor.putString("UserName", "");
                mEditor.putString("PassWord", "");
                mEditor.apply();
                Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();

                return false;
            }
        }
        return false;
    }
``` 
---

