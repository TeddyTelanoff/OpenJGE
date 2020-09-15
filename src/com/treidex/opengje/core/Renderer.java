package com.treidex.opengje.core;

import openjge.graphics.Mesh;

import static org.lwjgl.opengl.GL46.*;

public class Renderer {
    public void renderMesh(Mesh mesh) {
        glBindVertexArray(mesh.getVAO());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        glActiveTexture(GL_TEXTURE0);
        if (mesh.getMaterial().isTexture())
            glBindTexture(GL_TEXTURE_2D, mesh.getMaterial().getTextureID());
        mesh.getShader().bind();
        glDrawElements(GL_TRIANGLES, mesh.getIndices().length, GL_UNSIGNED_INT, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }
}
