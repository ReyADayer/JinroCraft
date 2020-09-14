FROM openjdk:8-jdk

ADD https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar BuildTools.jar
RUN java -Xmx1024M -jar BuildTools.jar --rev 1.15.2

ADD containers/spigot/eula.txt /eula.txt
ADD containers/spigot/ops.json /ops.json

ADD https://github.com/dmulloy2/ProtocolLib/releases/download/4.5.1/ProtocolLib.jar /plugins/ProtocolLib.jar
ADD build/libs/JinroCraft-1.1.2.jar /plugins/JinroCraft-1.1.2.jar