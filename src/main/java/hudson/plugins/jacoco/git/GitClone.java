/*******************************************************************************
 * Copyright (c) 2009, 2018 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Evgeny Mandrikov - initial API and implementation
 *
 *******************************************************************************/
package hudson.plugins.jacoco.git;

import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig.Host;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;

import java.io.File;
import java.io.IOException;


public class GitClone {

    /**
     * @param gitPath
     * @param tag
     * @param directory
     * @throws IOException
     * @throws GitAPIException
     */
    public static void cloneFiles(final String gitPath, final String tag, final String directory) throws IOException, GitAPIException {

        // 重写configure，设置"StrictHostKeyChecking","no"
        final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
            @Override
            protected void configure(final Host host, final Session session) {
                session.setConfig("StrictHostKeyChecking", "no");
            }
        };

        // 创建仓库目录
        final File tagPathFile = new File(directory);

        if (!tagPathFile.exists()) {// 如果已存在文件夹,则表示已clone过,不需要重新clone
            // 设置cloneCommand使用SSH的公钥认证
            final CloneCommand cloneCommand = Git.cloneRepository();
            cloneCommand
                    .setTransportConfigCallback(new TransportConfigCallback() {
                        public void configure(final Transport transport) {
                            final SshTransport sshTransport = (SshTransport) transport;
                            sshTransport
                                    .setSshSessionFactory(sshSessionFactory);
                        }
                    });

            // 克隆仓库
            cloneCommand.setBranch(tag).setURI(gitPath)
                    .setDirectory(tagPathFile).call();
        }

    }

    public static void main(String[] args) throws IOException, GitAPIException {
        GitClone.cloneFiles("git@192.168.23.204:IntelligentManage/djdata-cockpit.git", "djdata-cockpit_1.0.3.1", "C:\\Users\\xy\\Desktop\\新建文件夹 (2)");
    }

}
