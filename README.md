FBO
===

This project will pull down FBO feeds from https://www.fbo.gov and index them in elastic search.

###Setting up your environment
You will need to download groovy 2.2.1 from [here](http://dist.groovy.codehaus.org/distributions/groovy-binary-2.2.1.zip).  You can follow these [directions to install groovy](http://groovy.codehaus.org/Installing+Groovy).  You will also need elastic search.  [Download elastic](http://www.elasticsearch.org/download/).  The directions for getting elasticsearch up and running are [here](https://github.com/elasticsearch/elasticsearch/blob/master/README.textile).

The final step is to navigate to the scripts directory.  Make sure elasticsearch is running and execute the createIndex.sh script.  You're all ready to go.  

###Running the ES Importer
1.)  Go to the src directory.<br>
2.)  type ~> groovy downloadfbo.groovy -h<br>

Usage:<br>
    Download yesterday:   groovy downloadfbo.groovy<br>
    Download single day:  groovy downloadfbo.groovy yyyyMMdd<br>
    Download date range:  groovy downloadfbo.groovy yyyyMMdd(begin) yyyyMMdd(end)<br>

3.)  Run your import as stated by the help menu.<br>
4.)  You should now have a ./downloads directory in your project.<br>
5.)  Check ./downloads to make sure there are output files.<br>
6.)  type ~>  groovy fboesimport.groovy<br>
7.)  You should see the files get imported into ElasticSearch.<br>
8.)  Download and install [Kibana](http://www.elasticsearch.org/overview/kibana/).  Prepare to cyber-dazzle management.
