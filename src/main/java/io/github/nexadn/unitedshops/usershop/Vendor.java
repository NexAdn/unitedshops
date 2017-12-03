package io.github.nexadn.unitedshops.usershop;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import io.github.nexadn.unitedshops.UnitedShops;

public class Vendor {
    private Player                         owner;
    private String                         label;
    private float                          rating;
    private int                            buyCount;  // How many items were bought from the vendor
    private int                            sellCount; // How many items were sold to the vendor
    private HashMap<Material, Offer>       offers;
    private File                           saveFile;

    private static HashMap<Player, Vendor> vendors;

    /**
     * Create a new Vendor
     * 
     * @param creator
     *            The creator and initial owner
     * @param vendorLabel
     *            The label of the
     * @param vendorDataFolder
     *            The data folder where the vendor's yml file will be saved
     */
    public Vendor(Player creator, String vendorLabel, File vendorDataFolder)
    {
        this.buyCount = 0;
        this.sellCount = 0;
        this.rating = 0;
        this.owner = creator;
        this.label = vendorLabel;
        this.offers = new HashMap<Material, Offer>();
        this.saveFile = new File(vendorDataFolder, creator.getUniqueId().toString() + ".yml");

        if (vendors == null)
            vendors = new HashMap<Player, Vendor>();
        vendors.put(creator, this);
    }

    /**
     * Load an existing Vendor YAML file
     * 
     * @param dataFile
     *            The data file PLAYERUUID.yml
     */
    public Vendor(File dataFile)
    {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(dataFile);
        try
        {
            Player player = Bukkit.getPlayer(UUID.fromString(configuration.getString("vendor.name")));
        } catch (NullPointerException e)
        {
            UnitedShops.plugin.log(Level.SEVERE, "Failed to load user config!");
        }
    }

    public void init ()
    {
        // TODO: Init all Offers
    }

    public void addOffer (Offer o)
    {
        // TODO
    }

    private void saveToDisk ()
    {
        YamlConfiguration yamlConf = YamlConfiguration.loadConfiguration(this.saveFile);
        yamlConf.createSection("vendor");
        yamlConf.set("vendor.owner", this.owner.getUniqueId().toString());
        yamlConf.set("vendor.label", this.label);
        yamlConf.set("vendor.ratin", this.rating);
        yamlConf.set("vendor.buyCount", this.buyCount);
        yamlConf.set("vendor.sellCount", this.sellCount);
        yamlConf.createSection("vendor.offers");
        for (Offer o : this.offers.values())
        {

        }
    }
}

/*
 * Copyright (C) 2017 Adrian Schollmeyer
 * 
 * This file is part of UnitedShops.
 * 
 * UnitedShops is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
