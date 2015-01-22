package org.vnguyen.joreman;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.vnguyen.joreman.client.ForemanClient;
import org.vnguyen.joreman.client.ForemanClientFactory;
import org.vnguyen.joreman.model.HG;
import org.vnguyen.joreman.model.Host;
import org.vnguyen.joreman.model.Hostgroups;
import org.vnguyen.joreman.model.Hosts;

public class ForemanApiGettersTest {
    private ForemanClient foreman = null;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        foreman = ForemanClientFactory.createClient();
    }

    @Test
    public void getHostsTest() {
        Hosts hosts = foreman.api().getHosts(null, null, null, null);
        Assert.assertTrue(hosts.hosts.size() > 0,
                "Expected number of hosts is > 0");

        Host host = foreman.api().getHost(hosts.hosts.get(0).name);
        Assert.assertTrue(host.name.equals(hosts.hosts.get(0).name),
                "Returned host is expected to be named "
                        + hosts.hosts.get(0).name);

        // test per_page
        hosts = foreman.api().getHosts(null, null, null, "1");
        Assert.assertTrue(hosts.hosts.size() == 1,
                "Expected number of hosts is == 1");

        // test search
        String name = hosts.hosts.get(0).name;
        hosts = foreman.api().getHosts(name, null, null, "1");
        Assert.assertTrue(hosts.hosts.size() == 1,
                "Expected number of hosts is == 1");
        Assert.assertTrue(hosts.hosts.get(0).name.equals(name),
                "Returned host is expected to be named " + name);
    }

    @Test
    public void getHostgroupsTest() {
        Hostgroups hgs = foreman.api().getHostGroups(null, null, null, null);
        Assert.assertTrue(hgs.hostgroups.size() > 0,
                "Expected number of hostgroups is > 0");

        HG hg = foreman.api().getHostGroup(
                Integer.toString(hgs.hostgroups.get(0).id));
        Assert.assertTrue(
                hg.id.equals(hgs.hostgroups.get(0).id),
                "Returned hostgroup is expected to have id "
                        + hgs.hostgroups.get(0).id);

    }
}
