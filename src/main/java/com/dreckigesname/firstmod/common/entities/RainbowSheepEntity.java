package com.dreckigesname.firstmod.common.entities;

import java.util.ArrayList;


import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.core.init.EntityTypeInit;
import com.dreckigesname.firstmod.core.init.ItemInit;
import com.dreckigesname.firstmod.core.init.ParticleInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.w3c.dom.Attr;

public class RainbowSheepEntity extends AnimalEntity {

	public static final Ingredient TEMPTATION_ITEMS = Ingredient.of(ItemInit.RAINBOW_CRYSTAL.get(),
			ItemInit.RAINBOW_ORE.get(), ItemInit.CHARGED_RAINBOW_CRYSTAL.get());

	private EatGrassGoal eatGrassGoal;
	private int rainbowSheepTimer;

	private int fightingState = 1;
	private int attackState = 1;
	private Vector3d startingPosition;
	private int fightingTickCount = 0;

	boolean shockwave = false;

	private boolean isFighting = false;

	private ArrayList<Vector3d> lastPlayerPos = new ArrayList<Vector3d>();

	private Block[] woolBlocks = new Block[] { Blocks.RED_WOOL.getBlock(), Blocks.ORANGE_WOOL.getBlock(), Blocks.YELLOW_WOOL.getBlock(), Blocks.GREEN_WOOL.getBlock(), Blocks.BLUE_WOOL, Blocks.PURPLE_WOOL.getBlock() };
	private Potion[] potions = new Potion[] { Potions.REGENERATION, Potions.STRENGTH, Potions.POISON, Potions.FIRE_RESISTANCE, Potions.WEAKNESS, Potions.SWIFTNESS, Potions.LEAPING, Potions.SLOWNESS };
	private BasicParticleType[] particles = new BasicParticleType[] { ParticleInit.RED_PARTICLE.get(), ParticleInit.ORANGE_PARTICLE.get(), ParticleInit.YELLOW_PARTICLE.get(), ParticleInit.GREEN_PARTILE.get(), ParticleInit.BLUE_PARTICLE.get(), ParticleInit.PURPLE_PARTICLE.get() };

	private PlayerEntity targetPlayer;

	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenScreen(true);

	public RainbowSheepEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 2d).add(Attributes.MAX_HEALTH, 200d);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.eatGrassGoal = new EatGrassGoal(this);
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 0.7D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.5D));
		this.goalSelector.addGoal(5, this.eatGrassGoal);
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

	}

	@Override
	protected int getExperienceReward(PlayerEntity player) {
		return (int) random.nextFloat() * 50 + 50;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SHEEP_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.SHEEP_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SHEEP_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.SHEEP_STEP, 2.0f, 10.0f);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return EntityTypeInit.RAINBOW_SHEEP.get().create(this.level);
	}

	@Override
	protected void customServerAiStep() {
		this.rainbowSheepTimer = this.eatGrassGoal.getEatAnimationTick();
		super.customServerAiStep();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return super.hurt(source, amount);
	}

	public void stopFighting() {
		targetPlayer = null;
		fightingState = 1;
		fightingTickCount = 0;
		isFighting = false;
		lastPlayerPos.clear();
	}

	public void startFighting() {
		startingPosition = this.position();
		isFighting = true;
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void aiStep() {
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		if (this.level.isClientSide) {
			this.rainbowSheepTimer = Math.max(0, this.rainbowSheepTimer);
		} else {
			if (isFighting) {
				double pX = targetPlayer.getX();
				double pY = targetPlayer.getY();
				double pZ = targetPlayer.getZ();
				double eX = this.getX();
				double eY = this.getY();
				double eZ = this.getZ();
				double dist = FirstMod.distance(pX, pY, pZ, eX, eY, eZ);
				ServerWorld serverWorld = (ServerWorld) this.level;

				if (fightingState == 1) {
					this.setDeltaMovement(0, 0.2, 0);
					if (eY > startingPosition.y + 10) {
						fightingState = 2;
						attackState = 1;

						double r = 10;
						for (int i = 0; i < 30; i++) {
							Potion potionType = potions[random.nextInt(potions.length - 1)];
							PotionEntity potion = new PotionEntity(EntityType.POTION, serverWorld);
							potion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potionType));
							potion.setPos(eX, eY, eZ);
							potion.setDeltaMovement(random.nextFloat() * 2 * r - r, random.nextFloat() * 2 * r - r, random.nextFloat() * 2 * r - r);
							serverWorld.addFreshEntity(potion);
						}
					}
				}

				if (fightingState == 2) {

					if (attackState == 1) {
						lastPlayerPos.add(targetPlayer.position());
						Vector3d beamPos = lastPlayerPos.get(0);
						if (lastPlayerPos.size() > 15) {
							lastPlayerPos.remove(0);
						}

						this.setDeltaMovement(0, 0, 0);
						Vector3d looking = new Vector3d(beamPos.x - eX, beamPos.y - eY, beamPos.z - eZ).normalize();
						double beamDist = FirstMod.distance(beamPos.x, beamPos.y, beamPos.z, eX, eY, eZ);

						for (float i = 0; i < beamDist + 0.5; i += 0.5f) {
							Vector3d particlePos = this.position().add(looking.multiply(i, i, i));
							BasicParticleType particle = particles[(int) Math.min(i / beamDist * particles.length, particles.length - 1)];
							serverWorld.sendParticles(particle, particlePos.x, particlePos.y, particlePos.z, 1, 0.0D, 0.0D, 0.0D, 0.0);
							Block targetBlock = serverWorld.getBlockState(new BlockPos(particlePos)).getBlock();

							if (targetBlock != Blocks.AIR && targetBlock != Blocks.BEDROCK) {
								boolean replace = true;
								for (int r = 0; r < woolBlocks.length; r++) {
									if (targetBlock == woolBlocks[r]) {
										replace = false;
										break;
									}
								}
								if (replace) {
									serverWorld.destroyBlock(new BlockPos(particlePos), true);
									if (i > beamDist - 0.5) {
										Block randomWool = woolBlocks[ random.nextInt(woolBlocks.length)];
										serverWorld.setBlock(new BlockPos(particlePos), randomWool.defaultBlockState(), 3);
									}
									break;
								}
							}
						}

						if (FirstMod.distance(beamPos.x, beamPos.y, beamPos.z, pX, pY, pZ) < 1) {
							targetPlayer.hurt(DamageSource.mobAttack(this), 3f);
						}

						if (random.nextFloat() < 0.01) {
							attackState = 2;
						}
					}

					if (attackState == 2) {
						this.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 20, 4));
						if (this.isOnGround()) {
							shockwave = true;
							fightingTickCount = 0;
							startingPosition = this.position();
							this.removeAllEffects();
						}
						if (shockwave) {
							this.setDeltaMovement(0, 0.1, 0);
							for (double a = 0; a < Math.PI * 2; a += 0.05) {
								double x = eX + Math.cos(a) * fightingTickCount / 2;
								double z = eZ + Math.sin(a) * fightingTickCount / 2;
								double y = startingPosition.y;
								serverWorld.sendParticles(ParticleInit.YELLOW_PARTICLE.get(), x, y, z, 4, 0.0D, 0.0D, 0.0D, 0.2);
								serverWorld.sendParticles(ParticleInit.ORANGE_PARTICLE.get(), x, y, z, 4, 0.0D, 0.0D, 0.0D, 0.2);
								serverWorld.sendParticles(ParticleInit.RED_PARTICLE.get(), x, y, z, 4, 0.0D, 0.0D, 0.0D, 0.2);
								
								if (FirstMod.distance(x, y, z, pX, pY, pZ) < 2) {
									targetPlayer.setDeltaMovement(0, 1.3D, 0);
									targetPlayer.hurt(DamageSource.GENERIC, 0.1f);
								}
							}

							if (eY > startingPosition.y + 10) {
								shockwave = false;
								attackState = 1;
							}
						}
					}

					fightingTickCount++;
				}

				if (dist > 50 || targetPlayer.isDeadOrDying()) {
					stopFighting();
				}

			} else {
				targetPlayer = this.level.getNearestPlayer(this, 10);
				if (targetPlayer != null) {
					startFighting();
				}
			}
		}

		super.aiStep();
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.rainbowSheepTimer = 40;
		} else {
			super.handleEntityEvent(id);
		}
	}
}
