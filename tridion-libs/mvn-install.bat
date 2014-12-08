@echo off
rem Install Tridion Content Delivery libraries and necessary third-party libraries in the local Maven repository

echo Installing Tridion Content Delivery libraries into the local Maven repository...
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_ambient         -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_ambient-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_broker          -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_broker-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_cache           -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_cache-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_core            -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_core-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_datalayer       -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_datalayer-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_dynamic         -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_dynamic-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_linking         -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_linking-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_model           -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_model-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_session         -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_session-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cd_wrapper         -Dversion=7.1.0 -Dpackaging=jar -Dfile=cd_wrapper-7.1.0.jar

echo Installing Tridion Contextual Web Delivery libraries into the local Maven repository...
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cwd_cartridge      -Dversion=7.1.0 -Dpackaging=jar -Dfile=cwd_cartridge-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cwd_engine         -Dversion=7.1.0 -Dpackaging=jar -Dfile=cwd_engine-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cwd_image          -Dversion=7.1.0 -Dpackaging=jar -Dfile=cwd_image-7.1.0.jar
call mvn -q install:install-file -DgroupId=com.tridion -DartifactId=cwd_resource       -Dversion=7.1.0 -Dpackaging=jar -Dfile=cwd_resource-7.1.0.jar

echo Installing third-party libraries into the local Maven repository...
call mvn -q install:install-file -DgroupId=com.vs.ezlicrun -DartifactId=easylicense -Dversion=2.5 -Dpackaging=jar -Dfile=easylicense-2.5.jar
call mvn -q install:install-file -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar -Dfile=sqljdbc4-4.0.jar
call mvn -q install:install-file -DgroupId=ua_parser -DartifactId=ua-parser -Dversion=1.2.2 -Dpackaging=jar -Dfile=ua-parser-1.2.2.jar

echo Finished
