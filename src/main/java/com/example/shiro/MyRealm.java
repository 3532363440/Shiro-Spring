package com.example.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private DBUtil dbUtil;

    @Override  //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //得到登陆用户名
        Object name = principalCollection.getPrimaryPrincipal();

        //查询roles（角色）
        List<String > roles = dbUtil.getRolesByName(name.toString());
        //将数据库查出来的值循环给 info.addRole()
        for (String role : roles) {
            info.addRole(role);
        }

        //查询用户  权限
        List<String > list = dbUtil.getPermsByName(name.toString());
        info.addStringPermissions(list); //放在缓存中

        return info;
    }

    @Override  //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //得到登录的用户名
        Object principal = token.getPrincipal();
        System.out.println("用户名=" + principal);

        //根据用户名查询出密码
        String pwsd =dbUtil.getPasswordByName(principal.toString()) ;

        //当前数据 源的名字
        String realmName = getName();
        System.out.println("-----------------"+realmName+"------------------");

        //设置  盐值
        String source = "aaaaaaaaaa";//根据这个来加密
        ByteSource credentialsSalt = new Md5Hash(source);

        //用来身份认证 SimpleAuthenticationInfo（用户名，密码，数据源名字）
          //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,pwsd,realmName); //不加密的
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, pwsd,	credentialsSalt, realmName);

        return info;
    }

    /**
     * 在配置文件的数据源（Realm）中配置了一个 init-method="encipher"
     * 意思就是在初始化之前调用这个方法
     * */
    public void encipher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();

        matcher.setHashAlgorithmName("MD5"); //加密的样式
        matcher.setHashIterations(1024); //加密多少层

        setCredentialsMatcher(matcher);
    }

    /**
     * 打印加密  密码
     * */
    public static void main(String[] args) {

        String source = "aaaaaaaaaa";//根据这个来加密
        ByteSource credentialsSalt = new Md5Hash(source);
        //明文密码 + 盐值 + 层次
        Md5Hash m = new Md5Hash("789",credentialsSalt,1024);
        System.out.println(m);
    }

}
