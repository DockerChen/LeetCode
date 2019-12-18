import redis.clients.jedis.Jedis;

public class RedisJava {
    public static void main(String[] args) {
        // 连接远程Redis服务
        Jedis jedis = new Jedis("10.108.40.107", 6379);//Redis服务器的ip和port
        jedis.auth("redis");//Redis服务器访问密码
        System.out.println(" 连接成功 ");
        // 查看服务是否运行
        System.out.println(" 服务正在运行: " + jedis.ping());
    }
}