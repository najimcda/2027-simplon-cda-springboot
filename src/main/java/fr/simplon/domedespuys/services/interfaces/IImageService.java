package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.entities.Image;
import fr.simplon.domedespuys.services.interfaces.ICreate;
import fr.simplon.domedespuys.services.interfaces.IDelete;

public interface IImageService extends
        ICreate<Image, ImageDTO>,
        IDelete {
}