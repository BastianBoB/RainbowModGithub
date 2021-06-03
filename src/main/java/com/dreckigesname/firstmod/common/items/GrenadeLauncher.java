package com.dreckigesname.firstmod.common.items;

import com.dreckigesname.firstmod.common.entities.projectiles.BulletEntity;
import com.dreckigesname.firstmod.core.init.EntityTypeInit;
import com.dreckigesname.firstmod.core.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class GrenadeLauncher extends Item {

    public GrenadeLauncher(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Vector3d look = playerIn.getLookAngle();

        BulletEntity bullet = new BulletEntity(EntityTypeInit.BULLET_ENTITY.get(), worldIn);
        bullet.setPos(playerIn.getX(), playerIn.getY() + 1.5, playerIn.getZ());
        bullet.shoot(look.x, look.y, look.z, 5, 0);
        worldIn.addFreshEntity(bullet);

        return super.use(worldIn, playerIn, handIn);
    }
}
