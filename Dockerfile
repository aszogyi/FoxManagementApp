FROM quay.io/wildfly/wildfly:26.1.2.Final

COPY build/libs/FoxManagementApp-1.0.0.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]