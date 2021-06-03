package com.dreckigesname.firstmod.common.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InstantParticle extends SpriteTexturedParticle {

	protected InstantParticle(ClientWorld world, double x, double y, double z) {
		super(world, x, y, z);

		float f = 1.0f;
		this.rCol = f;
		this.bCol = f;
		this.gCol = f;

		this.setSize(0.2f, 0.2f);
		this.setSize(1, 1);
		this.xo = 0;
		this.yo = 0;
		this.zo = 0;
		this.lifetime = 2;
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}


	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {

		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			InstantParticle particle = new InstantParticle(worldIn, x, y, z);
			particle.pickSprite(sprite);
			particle.setColor(1.0f, 1.0f, 1.0f);
			return particle;
		}

	}
}
