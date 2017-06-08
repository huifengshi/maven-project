package com.starich.dataaccess.dao.es;

import com.google.common.net.HostAndPort;
import com.google.common.net.InetAddresses;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

/**
 * Created by Jason on 2017/3/19.
 */
public class ESClientFactory implements FactoryBean<TransportClient> {

    private String serverHostAndPort;
    private String clusterName;
    private TransportClient transportClient;

    private ESClientFactory(){

    }

    public TransportClient getObject() throws Exception {
        Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
        transportClient = TransportClient.builder().settings(settings).build();
        String[] hostAndPortArray = serverHostAndPort.split(",");
        for(int i = 0; i < hostAndPortArray.length; i++){
            HostAndPort hostAndPort = HostAndPort.fromString(hostAndPortArray[i]);
            transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddresses.forString(hostAndPort.getHost()), hostAndPort.getPort()));
        }
        return transportClient;
    }

    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void destroy(){
        if(transportClient != null){
            try{
                transportClient.close();
            }catch (Exception ex){
                //TODO log
            }
        }
    }

    public void setServerHostAndPort(String serverHostAndPort){
        this.serverHostAndPort = serverHostAndPort;
    }



    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
